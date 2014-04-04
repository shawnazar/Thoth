/**
 * @Project Thoth
 * @author Shawn Azar
 * @author Can Dalgir
 * @web-site http://www.projectthoth.com/
 */
package com.base.thoth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * <h2>Capture Activity</h2>
 * <p><i>Name Might Change</i></p>
 * <p>This class is used to capture the image. This class needs to be separated 
 * and parted into functions/methods/classes</p>
 */
public class CaptureActivity extends Activity 
{
	/**
	 * <h2>Debug Tag</h2>
	 * <p>Used to identify this activity in the debug stream<p>
	 * 
	 * @param  <i>Private</i> - <i>Constant</i> - <b>String</b> | Class name 
	 */
	private static final String DEBUG_TAG       = CaptureActivity.class.getSimpleName();

	/**
	 * <h2>Package Name</h2>
	 * <p>Used to identify this activity in the debug stream<p>
	 * 
	 * @param  <i>Public</i> - <i>Constant</i> - <b>String</b> | Package name 
	 */
	public static final String PACKAGE_NAME     = "com.base.thoth";
	
	/**
	 * <h2>Default Language</h2>
	 * <p>Needs changes - Add support for multi-lang</p>
	 * <p>current language<p>
	 * 
	 * @param  <i>Public</i> - <i>Constant</i> - <b>String</b> | language abbreviation
	 */
	public static final String DEFAULT_LANGUAGE = "eng";

	/**
	 * <h2>Data Path</h2>
	 * <p>Needs changes - Add support for internal storage</p>
	 * <p>Stores the current path to the application directory on the external storage<p>
	 * 
	 * @param  <i>Public</i> - <i>Constant</i> - <b>String</b> | Package location in external storage <br />ie. root application path
	 */
	public static final String DATA_PATH        = 
			Environment.getExternalStorageDirectory().toString() + "/Thoth/";

	/**
	 * <h2>Photo Taken</h2>
	 * <p>Text used for toast after successful image capture</p>
	 * 
	 * @param  <i>Protected</i> - <i>Constant</i> - <b>String</b> | literal: {@literal photo_taken}
	 */
	protected static final String PHOTO_TAKEN   = "photo_taken";
	
	/**
	 * <h2>Capture Image Button</h2>
	 * <p>Stored the button ID used to capture the image</p>
	 * 
	 * @param  <i>Protected</i> - <b>Button</b> | layout button id
	 */
	protected Button   captureImageButton;
	
	/**
	 * <h2>Copy Edit Text Content Button</h2>
	 * <p>Stored the text in the from the edit text view field into the users 
	 * master clip board</p>
	 * 
	 * @param  <i>Protected</i> - <b>Button</b> | layout button id
	 */
	protected Button   copyEditTextContentButton;
	
	/**
	 * <h2>Output Edit Text Field</h2>
	 * <p>Stored the text the id of the edit text view Field</p>
	 * 
	 * @param  <i>Protected</i> - <b>Button</b> | layout edit text id
	 */
	protected EditText outputEditTextFeild;
	
	/**
	 * <h2>Image Location</h2>
	 * <p>Stored the image location on device</p>
	 * 
	 * @param  <i>Protected</i> - <b>String</b> | Image location on device
	 */
	protected String   imageLocation;
	
	/**
	 * <h2>Image Captured Boolean</h2>
	 * <p>true if the user has decided to keep the captured image</p>
	 * 
	 * @param  <i>Protected</i> - <b>Boolean</b> | True when image is present
	 */
	protected boolean  imageCapturedBoolean;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };

		for (String path : paths) {
			File dir = new File(path);
			if (!dir.exists()) {
				if (!dir.mkdirs()) 
				{
					//Log.v(DEBUG_TAG, "ERROR: Creation of directory " + path + " on sdcard failed");
					Toast.makeText( getApplicationContext(),
									"ERROR: Creation of directory " + path + " on sdcard failed", 
									Toast.LENGTH_LONG).show();
					return;
				} else {
					//Log.v(DEBUG_TAG, "Created directory " + path + " on sdcard");
					Toast.makeText( getApplicationContext(),
									"Created directory " + path + " on sdcard", 
									Toast.LENGTH_LONG).show();
				}
			}

			
		}

		// lang.traineddata file with the app (in assets folder)
		// You can get them at:
		// http://code.google.com/p/tesseract-ocr/downloads/list
		// This area needs work and optimization
		if (!(new File(DATA_PATH + "tessdata/" + DEFAULT_LANGUAGE + ".traineddata")).exists()) 
		{
			try 
			{
				AssetManager assetManager = getAssets();
				InputStream in = assetManager.open("tessdata/" + DEFAULT_LANGUAGE + ".traineddata");
				OutputStream out = new FileOutputStream(DATA_PATH
						+ "tessdata/" + DEFAULT_LANGUAGE + ".traineddata");

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
			
				while ((len = in.read(buf)) > 0) 
				{
					out.write(buf, 0, len);
				}
				
				in.close();
				out.close();

				//Log.v(DEBUG_TAG, "Copied " + DEFAULT_LANGUAGE + " traineddata");
				Toast.makeText( getApplicationContext(),
								"Copied " + DEFAULT_LANGUAGE + " traineddata", 
								Toast.LENGTH_LONG).show();
			} catch (IOException e) 
			{
				//Log.e(DEBUG_TAG, "Was unable to copy " + DEFAULT_LANGUAGE + " traineddata " + e.toString());
				Toast.makeText( getApplicationContext(),
								"Was unable to copy " + DEFAULT_LANGUAGE + " traineddata " + e.toString(), 
								Toast.LENGTH_LONG).show();
			}
		}

		super.onCreate(savedInstanceState);

		setContentView(R.layout.capture_activity);

		// Defining elements
		outputEditTextFeild = (EditText) findViewById(R.id.editTextArea);
		captureImageButton = (Button) findViewById(R.id.captureImageButton);
		copyEditTextContentButton = (Button) findViewById(R.id.copyTextButton);
		captureImageButton.setOnClickListener(new ButtonClickHandler());
		copyEditTextContentButton.setOnClickListener(new ButtonClickHandler());

		imageLocation = DATA_PATH + "/ocr" 
		/**
		 * @fix
		 */
				//+ new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss_S").format(new Date())
		+ ".jpg";
	}

	@SuppressLint("NewApi") public class ButtonClickHandler implements View.OnClickListener {
	@Override
     public void onClick(View v) 
	{
         switch(v.getId())
         {
             case R.id.captureImageButton:
     				startCameraActivity();
             break;
          
             case R.id.copyTextButton:

            	 
            	 ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
            	 ClipData clip = ClipData.newPlainText(getPackageName(), outputEditTextFeild.getText());
            	 clipboard.setPrimaryClip(clip);
            	 Toast.makeText( getApplicationContext(),
            			 		 "Copied Text To Clipboard", 
								 Toast.LENGTH_LONG).show();
            	 
            	 
             break;
         }
	   }
	}

	public void startCameraActivity() 
	{
		File file = new File(imageLocation);
		Uri outputFileUri = Uri.fromFile(file);
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if (resultCode == -1) 
		{
			onPhotoTaken();
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(imageLocation))));
       	} 
		else 
		{
			//Log.v(DEBUG_TAG, "User cancelled");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) 
	{
		outState.putBoolean(CaptureActivity.PHOTO_TAKEN, imageCapturedBoolean);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) 
	{
		//Log.i(DEBUG_TAG, "onRestoreInstanceState()");
		if (savedInstanceState.getBoolean(CaptureActivity.PHOTO_TAKEN)) 
		{
			onPhotoTaken();
		}
	}

	protected void onPhotoTaken() 
	{
		imageCapturedBoolean = true;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;

		Bitmap bitmap = BitmapFactory.decodeFile(imageLocation, options);

		try 
		{
			ExifInterface exif = new ExifInterface(imageLocation);
			int exifOrientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			//Log.v(DEBUG_TAG, "Orient: " + exifOrientation);

			int rotate = 0;

			switch (exifOrientation) 
			{
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

			//Log.v(DEBUG_TAG, "Rotation: " + rotate);

			if (rotate != 0) 
			{
				int w = bitmap.getWidth();
				int h = bitmap.getHeight();

				Matrix mtx = new Matrix();
				mtx.preRotate(rotate);

				bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
			}
			bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

		} 
		catch (IOException e) 
		{
			Toast.makeText( getApplicationContext(),
							"Couldn't correct orientation: " + e.toString(), 
							 Toast.LENGTH_LONG).show();
		}

		TessBaseAPI baseApi = new TessBaseAPI();
//		baseApi.setDebug(true);
		baseApi.setDebug(false);
		baseApi.init(DATA_PATH, DEFAULT_LANGUAGE);
		baseApi.setImage(bitmap);

		String recognizedText = baseApi.getUTF8Text();
		baseApi.end();

		if ( DEFAULT_LANGUAGE.equalsIgnoreCase("eng") ) 
		{
			recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
		}

		recognizedText = recognizedText.trim();

		if ( recognizedText.length() != 0 ) 
		{
			outputEditTextFeild.setText(recognizedText);
			outputEditTextFeild.setSelection(outputEditTextFeild.getText().toString().length());
		}
		else
		{
			outputEditTextFeild.setText("Optical Recognition Failed, please attempt to capture the image again");
		}

	}
}