package com.example.edukate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ChoosePhotoActivity extends Activity {
	protected Uri mMediaUri;
	protected ImageView mProfilePicturePreview;
	protected Button mConfirmButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_photo);
		mConfirmButton = (Button) findViewById(R.id.uploadFinalButton);
		mConfirmButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ChoosePhotoActivity.this,
						EditProfileActivity.class);
				intent.setData(mMediaUri);
				startActivity(intent);

			}
		});
		mProfilePicturePreview = (ImageView) findViewById(R.id.profileImagePreview);

		mMediaUri = getIntent().getData();

		// Define the parseObject
		ParseObject photo = new ParseObject(ParseConstants.CLASS_IMAGE_NAME);
		// Create a byte Array variable for ParseFile creation
		if (mMediaUri == null) {
			Toast.makeText(ChoosePhotoActivity.this,
					"Error in chossing picture.Please try again.",
					Toast.LENGTH_LONG).show();
		} else {

			byte[] fileBytes;
			// Now we'll use code from github
			//
			fileBytes = FileHelper.getByteArrayFromFile(
					ChoosePhotoActivity.this, mMediaUri);
			// Check for any errors
			// Success
			// Create parse file
			ParseFile file = new ParseFile("image.png", fileBytes);
			// Attach this parse file to parse object message
			file.saveInBackground();
			photo.put(ParseConstants.KEY_FILE, file);
			photo.put("user", ParseUser.getCurrentUser().getObjectId());
			// saving in background
			photo.saveInBackground();
		}
		mProfilePicturePreview.setImageURI(mMediaUri);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_photo, menu);
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
