package com.example.edukate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AppInfo4 extends Fragment {
	protected TextView tv;
	protected Button signInButton;
	protected Button signUpButton;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_app_info_4,
				container, false);
		// Declare Text View
		tv = (TextView) rootView.findViewById(R.id.page4label);
		// Declare login Button
		signInButton = (Button) rootView.findViewById(R.id.loginButton);
		// setOnClickListener
		signInButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
			}
		});
		// Declare sign Up button
		signUpButton = (Button) rootView.findViewById(R.id.signUpButton);
		signUpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(getActivity(), SignupActivity.class);
				startActivity(intent2);

			}
		});
		return rootView;
	}

}
