package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;

@SuppressWarnings("unused")
public interface ExpandableListener extends OnMakeToastListener{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    ExpandableGroup getExpandableGroup(int viewType, LayoutInflater inflater, ViewGroup parent);
    ExpandableChild getExpandableChild(int viewType, LayoutInflater inflater, ViewGroup parent);
}
