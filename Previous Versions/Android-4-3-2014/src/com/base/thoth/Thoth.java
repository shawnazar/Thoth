package com.base.thoth;

import android.content.Context;
import android.widget.Toast;

public class Thoth 
{
	public static void toast(Context parentContext, String input)
	{
		Toast.makeText(parentContext, input, Toast.LENGTH_LONG).show();
	}
}
