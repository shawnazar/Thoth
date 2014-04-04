package com.base.thoth;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SpellCheckerActivity implements SpellCheckerSessionListener 
{
	public SpellCheckerActivity(String input)
	{	
//		mScs.getSpellChecker();
//		editText1 = mScs.getSentenceSuggestions(new TextInfo(input).getText(), 1);
	}
	
	@Override
	public void onGetSuggestions(SuggestionsInfo[] results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] results) {
		// TODO Auto-generated method stub
		
	}
}