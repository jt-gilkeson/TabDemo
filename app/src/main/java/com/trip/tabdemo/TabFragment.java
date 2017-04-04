package com.trip.tabdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trip.tabdemo.TabbedFragmentPagerAdapter.TitleProvider;

public class TabFragment extends Fragment implements TitleProvider
{
	private static final String TITLE = "title";

	protected String mTitle;

	public static TabFragment newInstance(String title)
	{
		TabFragment fragment = new TabFragment();
		Bundle args = fragment.createBundleWithTitle(title);
		fragment.setArguments(args);
		return fragment;
	}

	/**
	 * Helper method to create Bundle and set Title for TabFragment
	 *
	 * @param title the title to show
	 * @return Bundle with title set
	 */
	public Bundle createBundleWithTitle(String title)
	{
		Bundle bundle = new Bundle();

		if (title != null)
		{
			mTitle = title;
			bundle.putString(TITLE, title);
		}

		return bundle;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		mTitle = args.getString(TITLE);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		// Inflate the view
		View inflatedView = inflater.inflate(R.layout.tab_layout, container, false);

		// Get the UI elements
		TextView tv = (TextView)inflatedView.findViewById(R.id.tab_text);

		tv.setText(mTitle);

		return inflatedView;
	}

	@Override
	public String getTitle()
	{
		return mTitle;
	}
}
