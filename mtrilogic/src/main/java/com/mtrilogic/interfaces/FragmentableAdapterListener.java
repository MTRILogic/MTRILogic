package com.mtrilogic.interfaces;

import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;

@SuppressWarnings("unused")
public interface FragmentableAdapterListener extends OnMakeToastListener{
    FragmentableAdapter getFragmentableAdapter();
    ViewPager getViewPager();
}
