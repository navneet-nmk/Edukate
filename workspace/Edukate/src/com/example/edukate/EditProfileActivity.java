package com.example.edukate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;

public class EditProfileActivity extends Activity implements OnItemSelectedListener {

	// Declaration of member variables
	public String graduationYearLabel;
	protected Button mUploadButton;
	protected TextView mWelcomeMessage;
	protected ImageView mProfileImage;
	protected EditText mLocation;
	protected EditText mInterests;
	protected Button mContinueButton;
	protected Uri mMediaUri;
	protected Uri mPhotoUri;
	Spinner mYearSpinner;
	private String[] years = { "Set graduation Year", "2000", "2001", "2002",
			"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
			"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
			"2019", "2020", "2021" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		mPhotoUri = getIntent().getData();
		// Define the EditTexts
		mLocation = (EditText) findViewById(R.id.locationEditText);
		mInterests = (EditText) findViewById(R.id.interestEditText);
		
		// Define the buttton
		mContinueButton = (Button) findViewById(R.id.continueButton);
		// set onClick listener
		mContinueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String Location = mLocation.getText().toString();
				String Interests = mInterests.getText().toString();
				// Trim to get rid of white spaces
				Location.trim();
				Interests.trim();
				ParseUser user = ParseUser.getCurrentUser();
				user.put(ParseConstants.KEY_LOCATION, Location);
				user.put(ParseConstants.KEY_INTEREST, Interests);
				user.saveInBackground();
				Intent intent = new Intent(EditProfileActivity.this,
						QuestionsActivity.class);
				startActivity(intent);
			}
		});
		mUploadButton = (Button) findViewById(R.id.uploadButton);
		mProfileImage = (ImageView) findViewById(R.id.profileImageView);
		mWelcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
		// setting textview to show current user
		String username = ParseUser.getCurrentUser().getUsername();
		mWelcomeMessage.setText("Welcome " + username);
		// Uploading Image
		mUploadButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Uploading the image on parse
				Intent choosePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
				// Set type of file to choose ie only photos
				choosePhotoIntent.setType("image/*");
				startActivityForResult(choosePhotoIntent, 0);
			}
		});
		mProfileImage.setImageURI(mPhotoUri);

		mYearSpinner = (Spinner) findViewById(R.id.spinnerYears);
		// Set adapter for viewing the list of options
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, years);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mYearSpinner.setAdapter(adapter);
		mYearSpinner.setOnItemSelectedListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	// Defining onActivityResult listener
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == -1) {
			mMediaUri = data.getData();

		}
		Intent intent = new Intent(EditProfileActivity.this,
				ChoosePhotoActivity.class);
		intent.setData(mMediaUri);
		startActivity(intent);

	}

	// Method for spinner
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mYearSpinner.setSelection(position);
		graduationYearLabel = mYearSpinner.getSelectedItem().toString();
		ParseUser user = ParseUser.getCurrentUser();
		user.put(ParseConstants.KEY_GRADUATION_YEAR, graduationYearLabel);
		user.saveInBackground();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
