package com.mtrilogic.interfaces;

import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;

import java.util.ArrayList;

public interface FragmentListener extends OnChangePositionListener {
    FragmentableAdapter getFragmentableAdapter();
    ArrayList<Paginable> getPaginableList();
    ViewPager getViewPager();
}
