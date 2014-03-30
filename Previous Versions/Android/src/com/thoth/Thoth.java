package com.thoth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.content.ContextWrapper;
import android.os.Environment;
import android.util.Log;

/**
 * 	<h1>Thoth Class</h1>
 * 	<p>This Class is used to store constants, debugging methods, and methods 
 * used through out the program.</p>
 * 
 * @author  - Thoth Development Team
 * @version – 3.0
 */
public class Thoth 
{
	
	/**
	 * <h1>DEBUG_OUTPUT_STATUS - Private Static Final Boolean</h1>
	 * <p>Output debug statements when set to true</p>
	 */
	private static final boolean DEBUG_OUTPUT_STATUS = true;
	
	/**
	 * <h1>DEBUG_TAG - Private Static Final String</h1>
	 * <p>Output tag used in Logcat</p>
	 */
	private static final String DEBUG_TAG     = "Thoth_Debug";
	
	/**
	 * <h1>DATA_PATH - Public Static Final String</h1>
	 * <p>Location of the external storage</p>
	 */
	public static final String DATA_PATH = 
			Environment.getExternalStorageDirectory().toString() + "/Thoth/";
	
	/**
	 * <h1>DEFAULT_LANGUAGE - Public Static Final String</h1>
	 * <p>Default language used</p>
	 */
	public static final String DEFAULT_LANGUAGE = "eng";

	
	/**
	 * <h1>Debug Method</h1>
	 * 		This function is used to output thoth debug statements into a 
	 * channel for easier viewing. If <b>DEBUG_OUTPUT_STATUS</b> is set to 
	 * <b>TRUE</b> it will output statements in to Logcat Error channel 
	 * under <b>DEBUG_TAG</b>.
	 * 
	 * @param debugStatement - <i>String</i> - the statement to output
	 * 
	 * @return Void
	 */
	public static void Debug(String debugStatement)
	{
		if(DEBUG_OUTPUT_STATUS)
		{
			Log.e(DEBUG_TAG, DEBUG_TAG + " *|- " + debugStatement + " -|*");
		}
	}
	
	
}
