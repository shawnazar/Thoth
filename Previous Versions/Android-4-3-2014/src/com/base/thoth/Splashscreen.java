package com.base.thoth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.base.thoth.TutorialActivity;

public class Splashscreen extends Activity 
{

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) 
    {
        setContentView(R.layout.splashscrn);
        super.onCreate(icicle);
        getActionBar().hide();
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() 
            {
                /* Create an Intent that will start the Menu-Activity. */
                Intent tutorialActivity = new Intent(Splashscreen.this, TutorialActivity.class);
                Splashscreen.this.startActivity(tutorialActivity);
                Splashscreen.this.finish();
            }
            
        }, SPLASH_DISPLAY_LENGHT);
    }
}
