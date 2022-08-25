package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Mapable;

@SuppressWarnings("unused")
public interface ExpandableAdapterListener extends OnMakeToastListener {
    @NonNull
    ExpandableGroup<? extends Modelable> getExpandableGroup(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
    @NonNull
    ExpandableChild<? extends Modelable> getExpandableChild(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
    @NonNull
    Mapable<Modelable> getModelableMapable();
}
