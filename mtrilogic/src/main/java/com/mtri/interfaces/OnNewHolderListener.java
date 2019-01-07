package com.mtri.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface OnNewHolderListener{
    RecyclerView.ViewHolder onNewHolder(ViewGroup parent, int viewType);
}
