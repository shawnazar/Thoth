package com.thoth;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

// Headers
import com.thoth.Debug;
import com.thoth.Thoth;


public class CameraActivity 
{
	public static final int MEDIA_TYPE_IMAGE = 1;
	public Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private Uri fileUri;
	
	public CameraActivity()
	{
	    fileUri = getFileURI(); 
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); 
	}

	public Intent getIntent()
	{
		return intent;
	}
	
	/** Create a file Uri for saving an image */
	public static Uri getFileURI(){
	      return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image or video */
	public static File getOutputMediaFile()
	{
		File mediaFile = null;
		if(isSDPresent())
		{
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), Thoth.ALBUM_TITLE);
		    
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
		   
		    
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "THOTH_IMG_"+ timeStamp + ".jpg");
		        
		}
		else
		{
			// internal stroage
		}
			
	    return mediaFile;
	}
	public static boolean isSDPresent()
	{
		boolean isPresent = false;
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			isPresent = true;
		}
		
		Debug.LogCatE("isSDPresent Function isPresent value: " + isPresent);
		
		return isPresent;
	}
}
