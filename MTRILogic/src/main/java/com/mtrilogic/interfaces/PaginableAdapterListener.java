package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface PaginableAdapterListener extends AdapterListener, OnMakeToastListener {
    @NonNull
    Paginable<? extends Page> getPaginable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    @NonNull
    Listable<Page> getPageListable();
}
