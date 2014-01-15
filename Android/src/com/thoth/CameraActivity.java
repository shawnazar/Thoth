package com.thoth;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

// Headers
import com.thoth.Debug;
import com.thoth.Thoth;

public class CameraActivity 
{
	// Camera Intent Number - Used for imaging type
	public static final int MEDIA_TYPE_IMAGE = 1;
	// Create image capture intent
	public Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	// file location URI
	private Uri fileUri;
	
	// Default Constructor
	public CameraActivity()
	{
		Debug.LogCatE("Start Camera Activity Default Constructor");
		
		// Create the location of the file that will store the image
	    fileUri = getFileURI();
	    Debug.LogCatE("fileURI, toString - " + fileUri.toString());
	    
	    // Let the intent know of the location createad for the image
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	    Debug.LogCatE("intent, toString - " + intent.toString());
	    
	    Debug.LogCatE("End Camera Activity Default Constructor");
	}

	// Camera Activity Methods
	public static File getOutputMediaFile()
	{
		Debug.LogCatE("Start getOutputMediaFile Method");
		File mediaFile = null;
		
		boolean statusSD = isSDPresent();
		Debug.LogCatE("statusSD, toString - " + statusSD);
		
		// This section will change significantly
		// If an SD card is present choose sdcard
		if(statusSD)
		{
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), Thoth.ALBUM_TITLE);
			
		    Debug.LogCatE("mediaStorageDir, toString - " + mediaStorageDir.toString());
		    
		    // Create the storage directory if it does not exist
		    if (! mediaStorageDir.exists())
		    {
		        if (! mediaStorageDir.mkdirs())
		        {
		        	Debug.LogCatE("failed to create directory");
		        	return null;
		        }
		    }

		   // Create a media file name
		   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		   Debug.LogCatE("timeStamp, toString - " + timeStamp.toString());
		    
	       mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	       "THOTH_IMG_"+ timeStamp + ".jpg");
	       Debug.LogCatE("mediaFile, toString - " + mediaFile.toString());
		        
		}
		else
		{
			// internal stroage
			Debug.LogCatE("Internal Storage");
		}
		
		Debug.LogCatE("mediaFile, toString before return - " + mediaFile.toString());
		Debug.LogCatE("End getOutputMediaFile Method");
		
	    return mediaFile;
	}
	
	public static boolean isSDPresent()
	{
		Debug.LogCatE("Start isSDPresent Method");
		boolean isPresent = false;
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			isPresent = true;
		}
		Debug.LogCatE("mediaFile, toString - " + isPresent);
		
		
		Debug.LogCatE("End isSDPresent Method");
		return isPresent;
	}
	
	//Getters 
	public Intent getIntent()
	{
		Debug.LogCatE("Getter Return, toString - " + intent.toString());
		return intent;
	}
	
	public static Uri getFileURI()
	{
		  Debug.LogCatE("Getter Uri.fromFile");
	      return Uri.fromFile(getOutputMediaFile());
	}
}
