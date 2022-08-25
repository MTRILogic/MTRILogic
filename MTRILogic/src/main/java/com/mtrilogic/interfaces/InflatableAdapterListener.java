package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface InflatableAdapterListener extends OnMakeToastListener {
    @NonNull
    Inflatable<? extends Modelable> getInflatable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
    @NonNull
    Listable<Modelable> getModelableListable();
}
