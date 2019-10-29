package com.mtrilogic.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;

@SuppressWarnings("unused")
public interface RecyclableAdapterListener extends OnMakeToastListener{
    RecyclableAdapter getRecyclableAdapter();
    RecyclerView getRecyclerView();
}
