package com.thoth.fragments;

import com.thoth.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SnapShotFragment extends Fragment 
{
	
	public SnapShotFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
	{
 
        View rootView = inflater.inflate(R.layout.snap_shot, container, false);
        
        return rootView;
    }
}
