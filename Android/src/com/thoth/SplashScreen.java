package com.thoth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashScreen extends Activity 
{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
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
                // This method will be executed once the timer is over.
                Intent initialWindow = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(initialWindow);
 
                // close this activity
                finish();
                
            }//RUN - END
            
        }, SPLASH_TIME_OUT);
        
    }//ONCREATE - END
 
}//SPLASHSCREEN - END
