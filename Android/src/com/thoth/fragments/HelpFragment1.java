package com.thoth.fragments;

import com.thoth.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelpFragment1 extends Fragment 
{
	public HelpFragment1(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.help_tab1, container, false);
        
        return rootView;
    }
}
