package com.thoth;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.googlecode.tesseract.android.TessBaseAPI;

public class CameraActivity extends Activity{

	protected String _path;

	protected static final String PHOTO_TAKEN = "photo_taken";

	protected boolean _taken;
	
	private String recognizedText;
	
	public CameraActivity( )
	{
		Thoth.Debug("Starting Camera Activity Constructor");
		_path = Thoth.DATA_PATH + "/ocr.jpg";
		
		Thoth.Debug("Start Camera Activity");
		startCameraActivity();
	}

	protected void startCameraActivity() {
		File file = new File(_path);
		Uri outputFileUri = Uri.fromFile(file);

		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Thoth.Debug("resultCode: " + resultCode);

		if (resultCode == -1) {
			onPhotoTaken();
		} else {
			Thoth.Debug("User cancelled");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(PHOTO_TAKEN, _taken);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Thoth.Debug("onRestoreInstanceState()");
		if (savedInstanceState.getBoolean(PHOTO_TAKEN)) {
			onPhotoTaken();
		}
	}

	protected void onPhotoTaken() {
		_taken = true;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;

		Bitmap bitmap = BitmapFactory.decodeFile(_path, options);

		try {
			ExifInterface exif = new ExifInterface(_path);
			int exifOrientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			Thoth.Debug("Orient: " + exifOrientation);

			int rotate = 0;

			switch (exifOrientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				rotate = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				rotate = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				rotate = 270;
				break;
			}

			Thoth.Debug("Rotation: " + rotate);

			if (rotate != 0) {

				// Getting width & height of the given image.
				int w = bitmap.getWidth();
				int h = bitmap.getHeight();

				// Setting pre rotate
				Matrix mtx = new Matrix();
				mtx.preRotate(rotate);

				// Rotating Bitmap
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
			}

			// Convert to ARGB_8888, required by tess
			bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

		} catch (IOException e) {
			Thoth.Debug("Couldn't correct orientation: " + e.toString());
		}

		// _image.setImageBitmap( bitmap );
		Thoth.Debug("Before baseApi");

		TessBaseAPI baseApi = new TessBaseAPI();
		baseApi.setDebug(true);
		baseApi.init(Thoth.DATA_PATH, Thoth.DEFAULT_LANGUAGE);
		baseApi.setImage(bitmap);
		
		recognizedText = baseApi.getUTF8Text();
		
		baseApi.end();

		// You now have the text in recognizedText var, you can do anything with it.
		// We will display a stripped out trimmed alpha-numeric version of it (if lang is eng)
		// so that garbage doesn't make it to the display.
		Thoth.Debug("OCRED TEXT: " + recognizedText);
	
		if ( Thoth.DEFAULT_LANGUAGE.equalsIgnoreCase("eng") ) {
			recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
		}
		
		recognizedText = recognizedText.trim();
		Thoth.Debug("Trimed TEXT: " + recognizedText);
		
		// Cycle done.
	}

	public String getRecognizedText() {
		return recognizedText;
	}

}
