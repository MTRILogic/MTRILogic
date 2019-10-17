package com.mtrilogic.interfaces;

import android.view.ViewGroup;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;

@SuppressWarnings("unused")
public interface ExpandableListener extends OnMakeToastListener{
    ExpandableGroup getExpandableGroup(int viewType, ViewGroup parent);
    ExpandableChild getExpandableChild(int viewType, ViewGroup parent);
}
