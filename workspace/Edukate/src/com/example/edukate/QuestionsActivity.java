package com.example.edukate;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class QuestionsActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		ParseUser currentuser = ParseUser.getCurrentUser();
		// When user is logged in
		if (currentuser == null) {
			// For jumping straight into loginactivity class
			Intent intent = new Intent(this, LoginActivity.class);
			// To prevent back button going to mainactivity
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		} else {
			// Log the username in logcat for debugging purposes

		}

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionPagerAdapter(this,
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setOffscreenPageLimit(4);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.add_questions:
			final Dialog question = new Dialog(QuestionsActivity.this);
			question.setContentView(R.layout.dialoglayout);
			question.setTitle(R.string.question_add_dialog_text);
			Button postQuestionButton = (Button) question
					.findViewById(R.id.addQuestionButton);
			final EditText postQuestion = (EditText) question
					.findViewById(R.id.add_question_edittext);
			question.show();
			postQuestionButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ParseUser user = ParseUser.getCurrentUser();
					String questionAskedTxt = postQuestion.getText().toString();
					ParseObject questionPosted = new ParseObject(
							ParseConstants.CLASS_QUESTION_NAME);
					questionPosted.put(ParseConstants.KEY_QUESTION_ASKED,
							questionAskedTxt);
					questionPosted.put("user", user);
					questionPosted.put("Upvotes", 0);
					questionPosted.put("Downvotes", 0);
					questionPosted.saveInBackground();
					// Establishing a relation with the current user and the
					// question asked
					question.dismiss();

				}
			});

			break;
		case R.id.action_logout:
			ParseUser.logOut();
			Intent intent = new Intent(this, LoginActivity.class);
			// To prevent back button going to mainactivity
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			break;
		case R.id.add_post:
			final Dialog post = new Dialog(QuestionsActivity.this);
			post.setContentView(R.layout.dialoglayout);
			post.setTitle(R.string.add_post_text);
			Button addPostButtom = (Button) post
					.findViewById(R.id.addQuestionButton);
			final EditText addPostEditText = (EditText) post
					.findViewById(R.id.add_question_edittext);
			post.show();
			addPostButtom.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					ParseUser user = ParseUser.getCurrentUser();
					String posttext = addPostEditText.getText().toString();
					ParseObject postAdded = new ParseObject("Post");
					postAdded.put("postAdded", posttext);
					postAdded.put("user", user);
					postAdded.saveInBackground();
					post.dismiss();

				}
			});
		case R.id.instituteProfile:
			Intent instituteIntent = new Intent(QuestionsActivity.this,
					InstituteProfileActivity.class);
			startActivity(instituteIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

}
