package com.example.edukate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class AnswerActivity extends Activity {
	protected ListView answersListDisplay;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_two);
		//String question = getIntent().getStringExtra("question");
		// String objectId = getIntent().getStringExtra("questionID");
		//questionText = (TextView) findViewById(R.id.questiontext);
		//questionText.setText(question);
		answersListDisplay = (ListView) findViewById(R.id.answersList);
		CustomAdapterAnswers adapter = new CustomAdapterAnswers(
				AnswerActivity.this);
		answersListDisplay.setAdapter(adapter);

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
