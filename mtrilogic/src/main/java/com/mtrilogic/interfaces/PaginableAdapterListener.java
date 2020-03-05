package com.mtrilogic.interfaces;

import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.PaginableAdapter;

public interface PaginableAdapterListener extends OnMakeToastListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    PaginableAdapter getPaginableAdapter();
    ViewPager getViewPager();
}
