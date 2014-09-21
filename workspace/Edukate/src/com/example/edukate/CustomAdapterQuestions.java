package com.example.edukate;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;

public class CustomAdapterQuestions extends ParseQueryAdapter<ParseObject> {
	ParseRelation<ParseObject> relation;
	public CustomAdapterQuestions(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("question");
				// query.whereEqualTo("user", ParseUser.getCurrentUser()
				// .getObjectId());
				// query.findInBackground(new FindCallback<ParseObject>() {
				//
				// @Override
				// public void done(List<ParseObject> object, ParseException e)
				// {
				// if (e != null) {
				// Log.d("Error", e.getMessage());
				// }
				//
				// }
				// });

				return query;
			}
		});
	}

	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.questionslistview, null);
		}
		super.getItemView(object, v, parent);
		TextView questionsText = (TextView) v
				.findViewById(R.id.questionUserText);
		final TextView answerPosted = (TextView)v.findViewById(R.id.answerUserText);
		// Declare realtion to get answer
		relation = object.getRelation("answersRequired");
		relation.getQuery().getFirstInBackground(
				new GetCallback<ParseObject>() {

					@Override
					public void done(ParseObject arg0, ParseException e) {
						if (e == null) {
							answerPosted.setText(arg0.getString("answerPosted"));
						} else {
							Log.d("error", e.getMessage());
						}
					}
				});
		TextView upvotesText = (TextView) v
				.findViewById(R.id.upvotequestionUserText);
		questionsText.setText(object.getString("questionAsked"));
		
		String upvoteString = Integer.toString(object.getInt("Upvotes"));
		upvotesText.setText(upvoteString);
		return v;
	}
}
