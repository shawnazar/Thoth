package com.thoth;

import android.util.Log;

public class Debug 
{
	
	/* <b>Debug Information</b> */
	// Output Logcat debug statments
	public static final boolean DEBUG_STATUS = true;
	
	// Tag for LogCat
	public static final String DEBUG_TAG = "Thoth_Debug";
	
	/*
	 * Log Cat Error
	 **
	 *	Creates an output if Debugging is turned on.
	 */
	public static void LogCatE(String errorString)
	{
		if(DEBUG_STATUS == true)
		{
			Log.e(DEBUG_TAG, DEBUG_TAG + " *START|- " + errorString + " -|END*");
		}
	}
	
}
