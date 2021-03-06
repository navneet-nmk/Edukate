package com.example.edukate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapterAnswers extends ParseQueryAdapter<ParseObject> {
	// protected static ParseRelation<ParseObject> relation;

	public CustomAdapterAnswers(Context context) {

		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				ParseQuery<ParseObject> innerQuery = ParseQuery
						.getQuery("answer");

				return innerQuery;

			}

		});
	}

	@Override
	public View getItemView(final ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.answerslistviewlayout, null);
		}

		super.getItemView(object, v, parent);

		TextView answerstext = (TextView) v.findViewById(R.id.poststext);
		final Button upvoteAnswerButton = (Button) v
				.findViewById(R.id.upvoteButtonAnswers);
		upvoteAnswerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = object.getInt("Upvotes");
				count = count + 1;
				object.put("Upvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				upvoteAnswerButton.setEnabled(false);

			}
		});
		final Button downvoteAnswerButton = (Button) v
				.findViewById(R.id.downvoteButtonAnswers);
		downvoteAnswerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = object.getInt("Downvotes");
				count = count + 1;
				object.put("Downvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				downvoteAnswerButton.setEnabled(false);
			}
		});
		TextView upvotesAnswertext = (TextView) v
				.findViewById(R.id.upvotesTextAnswers);
		// relation = object.getRelation("answersRequired");
		// relation.getQuery().findInBackground(new FindCallback<ParseObject>()
		// {
		//
		// @Override
		// public void done(List<ParseObject> answers, ParseException e) {
		// if (e == null) {
		// for (ParseObject answer : answers) {
		// answerstext.setText(answer.getString("answerPosted"));
		// }
		// } else {
		// answerstext.setText("No answers yet");
		// }
		//
		// }
		// });

		if (object != null) {
			answerstext.setText(object.getString("answerPosted"));
			upvotesAnswertext.setText(object.getInt("Upvotes") + " upvotes");
		}

		return v;
	}
}
