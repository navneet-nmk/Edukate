package com.example.edukate;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAnswerActivity extends Activity {
	protected EditText answerEdittext;
	protected Button writeAnswer;
	protected Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final String objectId = getIntent().getStringExtra("questionAsked");
		setContentView(R.layout.activity_answer);
		answerEdittext = (EditText) findViewById(R.id.answerEditText);
		writeAnswer = (Button) findViewById(R.id.addanswerButton);
		cancel = (Button) findViewById(R.id.cancelButton);
		writeAnswer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String answerEntered = answerEdittext.getText()
						.toString();

				final ParseObject answerPosted = new ParseObject("answer");
				answerPosted.put("answerPosted", answerEntered);
				answerPosted.put("parent",
						ParseObject.createWithoutData("question", objectId));
				answerPosted.saveInBackground();

				ParseQuery<ParseObject> query = ParseQuery.getQuery("question");
				query.getInBackground(objectId, new GetCallback<ParseObject>() {

					@Override
					public void done(ParseObject object, ParseException e) {
						if (e == null) {
							object.put("child", ParseObject.createWithoutData(
									"answer", answerPosted.getObjectId()));
							ParseRelation<ParseObject> relation = object
									.getRelation("answersRequired");
							relation.add(answerPosted);
							object.saveInBackground();
						}

					}
				});
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer, menu);
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
