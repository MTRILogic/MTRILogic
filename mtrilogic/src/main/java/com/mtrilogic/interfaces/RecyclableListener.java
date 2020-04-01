package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Recyclable;

@SuppressWarnings("unused")
public interface RecyclableListener extends OnMakeToastListener {
    Recyclable getRecyclable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
