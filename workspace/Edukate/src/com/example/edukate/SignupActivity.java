package com.example.edukate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {
	// Declaration of member variables
	protected EditText mUsername;
	protected EditText mPassword;
	protected EditText mEmail;
	protected EditText mInstituteName;
	protected Button mSignUpButton;
	protected boolean emailVerified;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		// find view by id's for all the elements
		mUsername = (EditText) findViewById(R.id.usernameSignUp);
		mPassword = (EditText) findViewById(R.id.passwordSignUp);
		mEmail = (EditText) findViewById(R.id.emailSignUp);
		mInstituteName = (EditText) findViewById(R.id.instituteFiled);

		mSignUpButton = (Button) findViewById(R.id.signUpButtonTwo);
		// Set OnClickListener
		mSignUpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Declare strings for collecting
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				String email = mEmail.getText().toString();
				String institute = mInstituteName.getText().toString();
				// trim the data to get rid off whitespaces
				username.trim();
				password.trim();
				email.trim();
				institute.trim();
				// Check of empty fields
				if (username.isEmpty() || password.isEmpty() || email.isEmpty()
						|| institute.isEmpty()) {
					// Show alert dialog to the user
					AlertDialog.Builder builder = new AlertDialog.Builder(
							SignupActivity.this);
					builder.setMessage("Please enter valid username and password");
					builder.setTitle("Oops!");
					// Button to dismiss the dialog
					builder.setPositiveButton(android.R.string.ok, null);
					// Show the dialog
					AlertDialog dialog = builder.create();
					dialog.show();
				} else {
					final ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setEmail(email);
					user.setPassword(password);
					user.put(ParseConstants.KEY_INSTITUTE_NAME, institute);
					emailVerified = user
							.getBoolean(ParseConstants.KEY_EMAIL_VERIFIED);

					user.signUpInBackground(new SignUpCallback() {

						@Override
						public void done(ParseException e) {
							if (e == null) {
								// Success
								Toast.makeText(SignupActivity.this,
										"Account successfully created.",
										Toast.LENGTH_LONG).show();
								Intent intent = new Intent(SignupActivity.this,
										EditProfileActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							} else {
								Toast.makeText(
										SignupActivity.this,
										"There was a problem signing you up. Try Again.",
										Toast.LENGTH_LONG).show();
							}

						}

					});

				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
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
