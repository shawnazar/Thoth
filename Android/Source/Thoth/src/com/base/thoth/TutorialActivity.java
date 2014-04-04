/*
 * Copyright 2008 ZXing authors
 * Copyright 2011 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.base.thoth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Activity to display informational pages to the user in a WebView.
 * 
 * The code for this class was adapted from the ZXing project: http://code.google.com/p/zxing
 */
public final class TutorialActivity extends Activity 
{

  private static final String TAG = TutorialActivity.class.getSimpleName();

  // Use this key and one of the values below when launching this activity via intent. If not
  // present, the default page will be loaded.
  public static final String REQUESTED_PAGE_KEY = "requested_page_key";
  public static final String DEFAULT_PAGE = "whatsnew.html";
  public static final String ABOUT_PAGE = "about.html";
  public static final String TERMS_PAGE = "terms.html";
  public static final String WHATS_NEW_PAGE = "whatsnew.html";

  private static final String BASE_URL = "file:///android_asset/html/";
  private static final String WEBVIEW_STATE_PRESENT = "webview_state_present";

  private WebView webView;

  private final Button.OnClickListener doneListener = new Button.OnClickListener() {
    @Override
    public void onClick(View view) 
    {
    	/* Create an Intent that will start the Menu-Activity. */
        Intent tutorialActivity = new Intent(TutorialActivity.this, CaptureActivity.class);
        TutorialActivity.this.startActivity(tutorialActivity);
        TutorialActivity.this.finish();
        finish();
    }
  };

  @Override
  protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.tutorial_activity);

    webView = (WebView)findViewById(R.id.help_contents);
    
	webView.loadUrl("http://www.projectthoth.com/application_assets/android/tutorial/");
    
    View doneButton = findViewById(R.id.done_button);
    doneButton.setOnClickListener(doneListener);
  }

  @Override
  protected void onSaveInstanceState(Bundle state) {
    String url = webView.getUrl();
    if (url != null && url.length() > 0) {
      webView.saveState(state);
      state.putBoolean(WEBVIEW_STATE_PRESENT, true);
    }
  }

}