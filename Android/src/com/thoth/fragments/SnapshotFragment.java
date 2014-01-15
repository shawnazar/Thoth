package com.thoth.fragments;

import com.thoth.CameraActivity;
import com.thoth.Debug;
import com.thoth.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SnapshotFragment extends Fragment implements OnClickListener {

	// Camera Object Created
	CameraActivity image = new CameraActivity();
	
	public SnapshotFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.snap_shot, container, false);
        
        // Capture Button Creation
        Button captureButton = (Button) rootView.findViewById(R.id.captureButton);
        Debug.LogCatE("captureButton findViewById created");
        
        // Listener
        captureButton.setOnClickListener(this);
        
        return rootView;
    }
	
	@Override
	public void onClick(View v) 
	{
		// Button check
        switch (v.getId()) 
        {
        	// Capture Pic
	        case R.id.captureButton:
	        	
	        	Debug.LogCatE("Onclick for capture button");
         	    startActivityForResult(image.getIntent(), 1);
         	    
	            break;
        }
    }
}
