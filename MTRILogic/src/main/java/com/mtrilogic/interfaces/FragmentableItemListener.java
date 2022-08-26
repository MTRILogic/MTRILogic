package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface FragmentableItemListener extends FragmentableListener, ItemListener {
    @NonNull
    Listable<Paginable> getPaginableListable();

    @NonNull
    FragmentableAdapter getFragmentableAdapter();

    @NonNull
    ViewPager getViewPager();
}
