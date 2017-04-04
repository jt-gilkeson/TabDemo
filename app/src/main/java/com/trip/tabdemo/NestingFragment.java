package com.trip.tabdemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.lang.reflect.Field;

/**
 * Fragment that retains child fragment manager to fix issue:  https://code.google.com/p/android/issues/detail?id=74222
 */
public class NestingFragment extends Fragment
{
	private FragmentManager mRetainedChildFragmentManager;

	@Override
	public void onAttach(Context context)
	{
		// Deal with google support library bug https://code.google.com/p/android/issues/detail?id=74222
		// restore the last retained child fragment manager to the new created fragment
		if (mRetainedChildFragmentManager != null)
		{
			try
			{
				Field childField = Fragment.class.getDeclaredField("mChildFragmentManager");
				childField.setAccessible(true);
				childField.set(this, mRetainedChildFragmentManager);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		super.onAttach(context);
	}

	protected FragmentManager getRetainedChildFragmentManager()
	{
		if (mRetainedChildFragmentManager == null)
		{
			mRetainedChildFragmentManager = getChildFragmentManager();
		}
		return mRetainedChildFragmentManager;
	}
}
