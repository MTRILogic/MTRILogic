package com.mtrilogic.interfaces;

import android.view.ViewGroup;

import com.mtrilogic.abstracts.Recyclable;

public interface RecyclableListener extends OnMakeToastListener{
    Recyclable getRecyclable(int viewType, ViewGroup parent);
}
