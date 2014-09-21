package com.example.edukate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;

// New subclass
public class CustomAdapter extends ParseQueryAdapter<ParseObject> {
	protected ParseRelation<ParseObject> relation;

	public CustomAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				// TODO Auto-generated method stub
				// New class
				ParseQuery<ParseObject> innerQuery = ParseQuery
						.getQuery("question");

				return innerQuery;

			}

		});

	}

	public View getItemView(final ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listviewlayout, null);
		}
		super.getItemView(object, v, parent);
		Button answerQuestion = (Button) v.findViewById(R.id.ansWerButton);
		answerQuestion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(),
						AddAnswerActivity.class);
				intent.putExtra("questionAsked", object.getObjectId());
				getContext().startActivity(intent);
				notifyDataSetChanged();
			}
		});
		final Button downVote = (Button) v.findViewById(R.id.downvotePostsButton);
		downVote.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = object.getInt("Downvotes");
				count = count + 1;
				object.put("Downvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				downVote.setEnabled(false);

			}
		});
		final Button upvoteButton = (Button) v.findViewById(R.id.upvoteNewsButton);
		upvoteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int count = object.getInt("Upvotes");
				count = count + 1;
				object.put("Upvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				upvoteButton.setEnabled(false);

			}
		});

		final TextView answerPosted = (TextView) v
				.findViewById(R.id.poststext);
		// ParseQuery<ParseObject> query = ParseQuery.getQuery("question");
		// query.equals(object);
		// ParseQuery<ParseObject> innerquery = ParseQuery.getQuery("answer");
		// innerquery.whereMatchesQuery("question", query);
		// innerquery.getFirstInBackground(new GetCallback<ParseObject>() {
		//
		// @Override
		// public void done(ParseObject answer, ParseException e) {
		// // TODO Auto-generated method stub
		// }
		// });

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

		TextView upvotes = (TextView) v.findViewById(R.id.upvotePostText);
		String upvotesNumber = Integer.toString(object.getInt("Upvotes"));
		String downvotesNumber = Integer.toString(object.getInt("Downvotes"));
		upvotes.setText(upvotesNumber + " upvotes" + " and " + downvotesNumber
				+ " downvotes");

		TextView questionAsked = (TextView) v.findViewById(R.id.listText);

		questionAsked.setText(object.getString("questionAsked"));
		questionAsked.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), AnswerActivity.class);
				//intent.putExtra("question",
				//object.getString("questionAsked"));
				// intent.putExtra("questionID", object.getObjectId());
				getContext().startActivity(intent);

			}
		});
		return v;
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

}