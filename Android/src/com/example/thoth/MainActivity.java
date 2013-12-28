package com.example.thoth;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	/*****************************************************************
	 * MAIN
	 ***************************************************************** 
	 * On Create
	 ***************************************************************** 
	 * The on create method is the equivalent of main
	 *****************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Viewport object Creation
		final Button button = (Button) findViewById(R.id.CaptureButton);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				captureImageIntent(takePictureIntent);
				
				onActivityResult(RESULT_OK, takePictureIntent);
				
			}
		});

	}

	/*****************************************************************
	 * MENU OPTIONS
	 ** 
	 * On Create Options Menu
	 ***************************************************************** 
	 * The on Create options menu creates the menu options for the user
	 *****************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
