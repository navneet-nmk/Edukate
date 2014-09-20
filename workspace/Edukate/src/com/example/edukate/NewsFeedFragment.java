package com.example.edukate;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NewsFeedFragment extends Fragment {
	protected ListView postsList;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_news_feed,
				container, false);
		postsList = (ListView) rootView.findViewById(R.id.newsFeedList);
		// Set custom adapter to ther list view
		CustomAdapterNews adapterPost = new CustomAdapterNews(getActivity());
		Parcelable state = postsList.onSaveInstanceState();
		postsList.setAdapter(adapterPost);
		postsList.onRestoreInstanceState(state);

		return rootView;

	}

}
