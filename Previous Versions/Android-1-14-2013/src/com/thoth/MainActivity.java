package com.thoth;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends Activity 
{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 final Button capture = (Button) findViewById(R.id.captureButton);
		 final Button preview = (Button) findViewById(R.id.previewButton);
		 final ImageView imageView = (ImageView) findViewById(R.id.imageView);
		 
		 final CameraActivity image = new CameraActivity();
		 
		 capture.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	
            	image.capture();
         	    startActivityForResult(image.getIntent(), 1);
             }
                 
         });
		 
		 preview.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 imageView.setImageURI(image.getFileURI());
             }
                 
         });
		 
		
		
			

	    

	}	
}