package com.trip.tabdemo;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trip.tabdemo.SlidingTab.SlidingTabLayout;

public class TabbedFragment extends NestingFragment
{
	// UI Elements
	protected ViewPager                  mViewPager     = null;
	protected SlidingTabLayout           mSlidingTab    = null;
	protected TabbedFragmentPagerAdapter mTabbedAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Create our adapter
		FragmentManager fm = getRetainedChildFragmentManager();
		mTabbedAdapter = createTabbedAdapter(fm);

		setRetainInstance(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.tabbed_layout, container, false);
	}

	/**
	 * Hooks up UI elements to tabbed layout, makes actionbar transparent if the fragment is in a normal activity
	 */
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		// Get our UI elements
		mViewPager = (ViewPager)view.findViewById(R.id.pager);
		mSlidingTab = (SlidingTabLayout)view.findViewById(R.id.pager_tab_strip);

		// Get rid of line under action bar
		ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
		if (ab != null)
		{
			ab.setBackgroundDrawable(null);
		}

		// Set our tab appearance
		int iIndicatorColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
		ColorStateList textSelector = ContextCompat.getColorStateList(getContext(), R.color.selector_tab_color);

		mSlidingTab.setDistributeEvenly(true);
		mSlidingTab.setSelectedIndicatorColors(iIndicatorColor);
		mSlidingTab.setTextSelector(textSelector);

		updateTabs();

		super.onViewCreated(view, savedInstanceState);
	}

	/**
	 * Called during onCreate, this method is implemented by the subclass to create an instance of TabbedFragmentPagerAdapter and adds Fragments
	 */
	protected TabbedFragmentPagerAdapter createTabbedAdapter(FragmentManager fm)
	{
		TabbedFragmentPagerAdapter adapter = new TabbedFragmentPagerAdapter(fm);
		adapter.addItem(TabFragment.newInstance("Test 1"));
		adapter.addItem(TabFragment.newInstance("Test 2"));
		adapter.addItem(TabFragment.newInstance("Test 3"));

		return adapter;
	}

	/**
	 * Called during onViewCreated to hook up tabs to the viewpager and set the current selected item
	 */
	protected void updateTabs()
	{
		if (mTabbedAdapter != null)
		{
			// Associate our adapter with the viewpager
			mViewPager.setAdapter(mTabbedAdapter);
			mViewPager.setOffscreenPageLimit(2);

			// Associate the tabs with the viewpager
			mSlidingTab.setViewPager(mViewPager);

			// Set the current tab
			mViewPager.setCurrentItem(0);

			mViewPager.setVisibility(View.VISIBLE);
		}
	}
}
