package com.trip.tabdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabbedFragmentPagerAdapter extends FragmentPagerAdapter
{
	private List<TitleProvider> mFragments = new ArrayList<>();

	public TabbedFragmentPagerAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position)
	{
		if (position < mFragments.size())
		{
			return (Fragment)mFragments.get(position);
		}

		return null;
	}

	@Override
	public int getCount()
	{
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		TitleProvider frag = mFragments.get(position);
		return frag.getTitle();
	}

	public void addItem(TitleProvider frag)
	{
		mFragments.add(frag);
	}

	public interface TitleProvider
	{
		String getTitle();
	}

}
