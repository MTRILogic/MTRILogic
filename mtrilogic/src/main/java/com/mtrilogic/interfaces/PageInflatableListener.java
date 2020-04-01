package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.PageInflatable;

@SuppressWarnings("unused")
public interface PageInflatableListener extends OnMakeToastListener {
    PageInflatable getPageInflatable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
