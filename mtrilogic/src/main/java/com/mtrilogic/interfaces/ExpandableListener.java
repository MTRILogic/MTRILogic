package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.BindingExpandableChild;
import com.mtrilogic.abstracts.BindingExpandableGroup;

@SuppressWarnings("unused")
public interface ExpandableListener extends OnMakeToastListener {
    BindingExpandableGroup getExpandableGroup(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
    BindingExpandableChild getExpandableChild(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
