package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;

@SuppressWarnings("unused")
public interface ExpandableListener extends OnMakeToastListener{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    ExpandableGroup getExpandableGroup(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
    ExpandableChild getExpandableChild(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
