package com.thoth;

// Our Headers
import com.thoth.Constants;

// Imports
import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity 
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Constants.LogCatE("1 - Program Launched - OnCreate ");
		
		
	}	

}

