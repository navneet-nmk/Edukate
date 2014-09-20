package com.example.edukate;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
	protected TextView institute;
	protected TextView email;
	protected TextView userName;
	protected TextView location;
	protected ImageView profileImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profile, container,
				false);
		institute = (TextView) rootView.findViewById(R.id.userInstitute);
		email = (TextView) rootView.findViewById(R.id.userEmail);
		userName = (TextView) rootView.findViewById(R.id.profileName);
		location = (TextView) rootView.findViewById(R.id.userDOB);
		profileImage = (ImageView) rootView.findViewById(R.id.profilePicture);
		ParseUser user = ParseUser.getCurrentUser();
		institute.setText(user.getString("institute"));
		email.setText(user.getEmail());
		userName.setText(user.getUsername());
		location.setText(user.getString("location"));
		ParseQuery<ParseObject> query = ParseQuery.getQuery("image");
		query.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					ParseFile image = object.getParseFile("file");
					Uri mMediaUri = Uri.parse(image.getUrl());
					Picasso.with(getActivity()).load(mMediaUri.toString())
							.into(profileImage);

				}

			}
		});

		return rootView;

	}
}
