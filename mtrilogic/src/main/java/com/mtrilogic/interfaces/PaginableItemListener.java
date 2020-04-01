package com.mtrilogic.interfaces;

import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.PaginableAdapter;

@SuppressWarnings("unused")
public interface PaginableItemListener extends ItemListener {
    PaginableAdapter getPaginableAdapter();
    ViewPager getViewPager();
}
