package com.thoth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.thoth.R;

import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class MainActivity extends Activity 
{
	protected Button _button;
	protected TextView _field;
	protected CameraActivity CameraObject;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	
		String[] paths = new String[] { Thoth.DATA_PATH, Thoth.DATA_PATH + "tessdata/" };

		for (String path : paths) {
			File dir = new File(path);
			if (!dir.exists()) {
				if (!dir.mkdirs()) 
				{
					Thoth.Debug("ERROR: Creation of directory " + path + " on sdcard failed");
					return;
				} else {
					Thoth.Debug("Created directory " + path + " on sdcard");
				}
			}

		}
		
		// lang.traineddata file with the app (in assets folder)
		// You can get them at:
		// http://code.google.com/p/tesseract-ocr/downloads/list
		// This area needs work and optimization
		if (!(new File(Thoth.DATA_PATH + "tessdata/" + Thoth.DEFAULT_LANGUAGE + ".traineddata")).exists()) {
			try {

				AssetManager assetManager = getAssets();
				InputStream in = assetManager.open("tessdata/" + Thoth.DEFAULT_LANGUAGE + ".traineddata");
				//GZIPInputStream gin = new GZIPInputStream(in);
				OutputStream out = new FileOutputStream(Thoth.DATA_PATH
						+ "tessdata/" + Thoth.DEFAULT_LANGUAGE + ".traineddata");

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				//while ((lenf = gin.read(buff)) > 0) {
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				//gin.close();
				out.close();
				Thoth.Debug("Copied " + Thoth.DEFAULT_LANGUAGE + " traineddata");
			} catch (IOException e) {
				Thoth.Debug("Was unable to copy " + Thoth.DEFAULT_LANGUAGE + " traineddata " + e.toString());
			}
		}
    	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// _image = (ImageView) findViewById(R.id.image);
		_field = (TextView) findViewById(R.id.textView1);
		_button = (Button) findViewById(R.id.button1);
		_button.setOnClickListener(new ButtonClickHandler());

		
	}
    public class ButtonClickHandler implements View.OnClickListener {
		public void onClick(View view) {
			Thoth.Debug("Starting Camera app");
			CameraObject = new CameraActivity();
			Thoth.Debug(CameraObject.getRecognizedText());
		}
	}

	

}
