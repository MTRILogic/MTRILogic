package com.mtrilogic.interfaces;

import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.adapters.FragmentableStateAdapter;

@SuppressWarnings("unused")
public interface FragmentableAdapterListener extends OnMakeToastListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    FragmentableStateAdapter getFragmentableStateAdapter();
    FragmentableAdapter getFragmentableAdapter();
    ViewPager getViewPager();
}
