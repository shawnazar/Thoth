package com.thoth;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment.SavedState;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button captureButton = (Button) this.findViewById(R.id.captureButton);

		captureButton.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				takePhoto(v);
				onActivityResult(TAKE_PICTURE, RESULT_OK, intent);
			}
		});
	}
	
	//pv
	private final static int TAKE_PICTURE = 1;    
	private Uri imageUri;
	public static final int MEDIA_TYPE_IMAGE = 1;

	public void takePhoto(View view) {
	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    File photo = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator,  "Pic.jpg");
	    imageUri = Uri.fromFile(photo);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT,
	    		imageUri);
	    
	    startActivityForResult(intent, TAKE_PICTURE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    
	    if (requestCode == TAKE_PICTURE) 
	    {
	        if (resultCode == Activity.RESULT_OK) 
	        {
	            Uri selectedImage = imageUri;
	            getContentResolver().notifyChange(selectedImage, null);

	            String filename = "pippo.jpg";
	            File sd = Environment.getExternalStorageDirectory();
	            File dest = new File(sd, filename);

	            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
	            try {
	                 FileOutputStream out = new FileOutputStream(dest);
	                 bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
	                 out.flush();
	                 out.close();
	            } catch (Exception e) {
	                 e.printStackTrace();
	            }
	            
	           
	         }
	            
	        }
	    }
	}

