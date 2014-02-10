package com.thoth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
 
/* MAIN STARTING SCREEN
 * All the needed process will be done here at starting point.
 * Check if there connection to the Internet, if there is no connection to the Internet
 * Run error  message. */
public class SplashScreen extends Activity 
{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    private boolean haveNetworkConnection() 
    {
        boolean haveConnectedWifi   = false;
        boolean haveConnectedMobile = false;
        boolean noConnection        = false;
        boolean connectionTRON      = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) 
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
            {
            	if (ni.isConnected())
            	{
            		 haveConnectedWifi = true;
            	}
                   
            } 
            else if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
            {
            	 if (ni.isConnected())
            	 {
            		 haveConnectedMobile = true;
            	 }
                     
            }
            else
            {
            	noConnection = false;
            }
            
            if(haveConnectedWifi || haveConnectedMobile)
            {
            	connectionTRON = true;
            	noConnection   = true;//turned ON.
            }
               
        }
        
        return connectionTRON || noConnection;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        /* Content view is set to the splash-screen -> splashscrn.xml */
        setContentView(R.layout.splashscrn);
        
 
        /* Handler is created for the splash-screen. */
        new Handler().postDelayed(new Runnable() 
        {
            @Override
            public void run() 
            {
            	
            	if(haveNetworkConnection())
            	{
                    // This method will be executed once the timer is over.
                    Intent initialWindow = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(initialWindow);
            	}
            	else
            	{
                    // Switching to LogIn Screen.
                    Intent mainPg = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainPg);
            	}
 
 
                // close this activity
                finish();
                
            }//RUN - END
            
        }, SPLASH_TIME_OUT);
        
    }//ONCREATE - END
 
}//SPLASHSCREEN - END
