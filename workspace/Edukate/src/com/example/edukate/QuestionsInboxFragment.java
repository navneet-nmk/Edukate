package com.example.edukate;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class QuestionsInboxFragment extends Fragment {
	// Member variable for list of questions
	// protected List<ParseObject> mQuestions;
	protected SwipeRefreshLayout mSwipeRefreshLayout;
	protected ListView questionList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_questions,
				container, false);
		setRetainInstance(true);
		questionList = (ListView) rootView.findViewById(R.id.questionsList);
		// // Declare a parse query
		// ParseUser user = ParseUser.getCurrentUser();
		// ParseQueryAdapter<ParseObject> adapter = new
		// ParseQueryAdapter<ParseObject>(
		// getActivity(),
		// new ParseQueryAdapter.QueryFactory<ParseObject>() {
		//
		// @Override
		// public ParseQuery<ParseObject> create() {
		// ParseQuery<ParseObject> query = new ParseQuery<>("question");
		// // query.whereEqualTo("user", ParseUser.getCurrentUser()
		// // .getObjectId());
		// return query;
		// }
		// });
		// adapter.setTextKey(ParseConstants.KEY_QUESTION_ASKED);
		CustomAdapter adapter = new CustomAdapter(getActivity());
		questionList.setAdapter(adapter);
		return rootView;

	}

	// Defining OnResume method in order to refresh the inbox from time to time
	// In a fragment onResume is supposed to be public
	@Override
	public void onResume() {
		super.onResume();
		// For progress bar we need the activity method

	}

}
