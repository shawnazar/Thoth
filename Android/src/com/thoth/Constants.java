package com.thoth;

import android.util.Log;

public class Constants 
{
	// Output Logcat debug statments
	public static final boolean DEBUG_STATUS = true;
	
	// Tag for LogCat
	public static final String DEBUG_TAG = "Thoth_Debug";
	
	public static void LogCatE(String errorString)
	{
		if(DEBUG_STATUS == true)
		{
			Log.e(DEBUG_TAG, errorString);
		}
	}
}
