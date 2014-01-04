package com.thoth;

// Our Headers
import com.thoth.Constants;

// Imports
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity 
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.e(Constants.DEBUG_TAG, "1 - Program Launched - OnCreate ");
	}	

}

