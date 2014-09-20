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
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.newsfeedlistitem, null);
		}
		super.getItemView(object, v, parent);
		TextView postAdded = (TextView) v.findViewById(R.id.poststext);
		postAdded.setText(object.getString("postAdded"));
		TextView upvotestext = (TextView) v.findViewById(R.id.upvotePostText);
		Button upvoteButton = (Button) v.findViewById(R.id.upvoteNewsButton);
		Button downvButton = (Button) v.findViewById(R.id.downvotePostsButton);
		return v;
	}
}
