package com.thoth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
/* LoginActivity 
 * 
 * 	+	This section will run if there is a Internet connection.
 *  +	Or OFFLINE MODE.
 *  
 *  This class will execute the login page for the program.
 *  It handles connections to: register, main-page.*/
public class LoginActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login);
 
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        Button mainScreen = (Button) findViewById(R.id.btnLogin);
 
        /* On click register event.
         * Executes register activity for a new user. */
        registerScreen.setOnClickListener(new View.OnClickListener() 
        {
 
            public void onClick(View v) 
            {
                // Intent for the register page is created here for on_click event.
                Intent regPage = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regPage);
            }
        });//registerScreen - END
        
        /* On click login event.
         * Executes login activity for user. */
        mainScreen.setOnClickListener(new View.OnClickListener() 
        {
 
            public void onClick(View v) 
            {
                // Switching to Register screen
                Intent mainPg = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainPg);
            }
        });//mainScreen - END
        
    }// onCreate - End
}
