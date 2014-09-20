package com.example.edukate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppInfo2 extends Fragment{
	protected TextView tv;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_app_info, container,
				false);
		tv = (TextView)rootView.findViewById(R.id.page1Text);
		tv.setText(R.string.page2_label);
		return rootView;
	}

}
