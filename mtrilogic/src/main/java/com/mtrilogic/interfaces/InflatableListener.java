package com.mtrilogic.interfaces;

import android.view.ViewGroup;

import com.mtrilogic.abstracts.Inflatable;

@SuppressWarnings("unused")
public interface InflatableListener extends OnMakeToastListener{
    Inflatable getInflatable(int viewType, ViewGroup parent);
}
