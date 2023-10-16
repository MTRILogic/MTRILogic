package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface PaginableItemListener extends ItemListener, PaginableListener{

    @NonNull
    PaginableAdapter getPaginableAdapter();

    @NonNull
    Listable<Page> getPageListable();

    @NonNull
    ViewPager getViewPager();
}
