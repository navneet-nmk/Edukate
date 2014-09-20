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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mLoginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUsername = (EditText) findViewById(R.id.usernameField);
		mPassword = (EditText) findViewById(R.id.passwordFiled);
		mLoginButton = (Button) findViewById(R.id.signInButtonFiled);
		// Set on Click Listener
		mLoginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();

				// Trim to get rid of white spaces
				username.trim();
				password.trim();

				// Log in background
				if (username.isEmpty() || password.isEmpty()) {
					// Alert the user of errors using dialogs

					AlertDialog.Builder builder = new AlertDialog.Builder(
							LoginActivity.this);
					builder.setMessage("Please enter valid username and password");
					builder.setTitle("Oops!");
					// Button to dismiss the dialog
					builder.setPositiveButton(android.R.string.ok, null);
					// Show the dialog
					AlertDialog dialog = builder.create();
					dialog.show();
				} else {

					ParseUser.logInInBackground(username, password,
							new LogInCallback() {

								@Override
								public void done(ParseUser user,
										ParseException e) {
									// TODO Auto-generated method stub
									if (e == null) {
										// success
										Intent intent = new Intent(
												LoginActivity.this,
												QuestionsActivity.class);
										intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
										// Going to the questions area
										startActivity(intent);
									} else {
										// Alert the user of errors using
										// dialogs

										AlertDialog.Builder builder = new AlertDialog.Builder(
												LoginActivity.this);
										builder.setMessage("Error logging in. Please try again.");
										builder.setTitle("Oops!");
										// Button to dismiss the dialog
										builder.setPositiveButton(
												android.R.string.ok, null);
										// Show the dialog
										AlertDialog dialog = builder.create();
										dialog.show();
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
		getMenuInflater().inflate(R.menu.login, menu);
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
