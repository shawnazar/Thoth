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
		Debug.LogCatE("Constructor - Camera Activity");
	    
		fileUri = getFileURIFromFile();
	    
	    Debug.LogCatE("FileURI set. FileURI toString: " + fileUri.toString());
	}
	
	public void capture()
	{
		Debug.LogCatE("Method - capture");
		
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		
		Debug.LogCatE("Intent Created with extra option of uri. Intent toString: " + intent.toString());
	}
	
	public Uri getFileURI()
	{
		Debug.LogCatE("Method - getFileURI");
		Debug.LogCatE("fileURI toString:  " + fileUri.toString());
		return fileUri;
	}
	
	public Intent getIntent()
	{
		Debug.LogCatE("Method - getIntent");
		Debug.LogCatE("intent toString:  " + intent.toString());
		return intent;
	}
	
	
	public static Uri getFileURIFromFile()
	{
		Debug.LogCatE("Method - getFileURIFromFile");
		Debug.LogCatE("This method Calls getOutputMediaFile() before it returns." +
					  "getOutputMediaFile() toString: " + getOutputMediaFile().toString());
		return Uri.fromFile(getOutputMediaFile());
	}

	/** Create a File for saving an image or video */
	public static File getOutputMediaFile()
	{
		Debug.LogCatE("Method - getOutputMediaFile()");

		File mediaFile = null;
		
		// If the device uses an SDcard
		if(isSDPresent())
		{
			// Create the varaiable that will store the file.
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
		              Environment.DIRECTORY_PICTURES), Thoth.ALBUM_TITLE);
		    
			// Make Try Catch
		    // Create the storage directory if it does not exist
		    if (! mediaStorageDir.exists())
		    {
		        if (! mediaStorageDir.mkdirs())
		        {
		        	Debug.LogCatE("failed to create directory");
		        	return null;
		        }
		    }
		    
		    // Create a media file name time stamp
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    String fileName  = mediaStorageDir.getPath() + File.separator +
			        "THOTH_IMG_" + timeStamp + ".png";
		    
		    // store the file.
	        mediaFile = new File(fileName);
		        
		}
		else
		{
			// internal stroage
		}
		
		
	    return mediaFile;
	}
	
	public static boolean isSDPresent()
	{
		Debug.LogCatE("Method - IsSDPresent()");
		boolean isPresent = false;
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			isPresent = true;
		}
		
		Debug.LogCatE("isSDPresent value: " + isPresent);
		
		return isPresent;
	}
}
