package com.example.edukate;

import android.app.Application;

import com.parse.Parse;

public class EdukateApplication extends Application {
	public void onCreate() {
		
		Parse.initialize(this, "79YdBk4ZGsN76jbeRIRNElmp3nkrW9bEqBNS95fb",
				"Y1gb4YKK44fgfUL15KGQw3da3UHIMoyhnZL10v1A");
	
	}
}
