package com.example.edukate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapterNews extends ParseQueryAdapter<ParseObject> {
	public CustomAdapterNews(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				// Declare a ParseQuery to display all the posts
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
				return query;
			}
		});
	}

	@Override
	public View getItemView(final ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.newsfeedlistitem, null);
		}
		super.getItemView(object, v, parent);
		TextView postAdded = (TextView) v.findViewById(R.id.poststext);
		postAdded.setText(object.getString("postAdded"));
		TextView upvotestext = (TextView) v.findViewById(R.id.upvotePostText);
		final Button upvoteButton = (Button) v
				.findViewById(R.id.upvoteNewsButton);
		upvoteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int count = object.getInt("Upvotes");
				count = count + 1;
				object.put("Upvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				upvoteButton.setEnabled(false);

			}
		});

		final Button downvButton = (Button) v
				.findViewById(R.id.downvotePostsButton);
		downvButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int count = object.getInt("Downvotes");
				count = count + 1;
				object.put("Downvotes", count);
				object.saveInBackground();
				notifyDataSetChanged();
				downvButton.setEnabled(false);

			}
		});
		upvotestext.setText(object.getInt("Upvotes") + " upvotes " + "and "
				+ object.getInt("Downvotes") + " downvotes.");
		
		return v;
	}
}
