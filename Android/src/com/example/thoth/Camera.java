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

public class Camera extends Activity {
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public boolean deviceCameraStatus = false;
	public int resultStatusCode = 0;
	public Camera() 
	{
		System.out.println("Camera Object Created");
		
		deviceCameraStatus = checkCameraHardware(getBaseContext());
		
		System.out.println("Camera Status : " + deviceCameraStatus);
		
	}

	public void captureImageIntent(Intent obj) {

		if (obj.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(obj, REQUEST_IMAGE_CAPTURE);
		}
	}

	public void onActivityResult(int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			// Image captured and saved to fileUri specified in the Intent
			Toast.makeText(this, "Image saved to:\n" + data.getData(),
					Toast.LENGTH_LONG).show();
		} else if (resultCode == RESULT_CANCELED) {
			// User cancelled the image capture
		} else {
			// Image capture failed, advise user
		}
	}

	/** Check if this device has a camera */
	public boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}
}
