package com.mtrilogic.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface OnViewTypeAndHolderListener extends OnGetViewTypeListener{
    RecyclerView.ViewHolder onNewHolder(ViewGroup parent, int viewType);
}
