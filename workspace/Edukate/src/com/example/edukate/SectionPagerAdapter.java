package com.example.edukate;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionPagerAdapter extends FragmentStatePagerAdapter {

	protected Context mContext;

	public SectionPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		switch (position) {
		case 0:
			return new QuestionsInboxFragment();
		case 1:
			return new NewsFeedFragment();
		case 2:
			return new ProfileFragment();
		}
		return new QuestionsInboxFragment();
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.title_section4).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.title_section5).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.title_section6).toUpperCase(l);
		}
		return null;
	}
}
