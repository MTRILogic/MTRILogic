package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface RecyclableAdapterListener extends AdapterListener, OnMakeToastListener {

    @NonNull
    Recyclable<? extends Model> getRecyclable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    @NonNull
    Listable<Model> getModelListable();
}
