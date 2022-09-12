package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.classes.Mappable;

@SuppressWarnings("unused")
public interface ExpandableAdapterListener extends AdapterListener, OnMakeToastListener {
    @NonNull
    ExpandableGroup<? extends Model> getExpandableGroup(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    @NonNull
    ExpandableChild<? extends Model> getExpandableChild(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    @NonNull
    Mappable<Model> getModelMappable();
}
