package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface FragmentableItemListener extends ItemListener, FragmentableListener{

    @NonNull
    FragmentableAdapter getFragmentableAdapter();

    @NonNull
    Listable<Page> getPageListable();

    @NonNull
    ViewPager getViewPager();
}
