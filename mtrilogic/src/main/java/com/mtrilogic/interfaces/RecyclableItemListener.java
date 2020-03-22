package com.mtrilogic.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;

@SuppressWarnings("unused")
public interface RecyclableItemListener extends ItemListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    RecyclableAdapter getRecyclableAdapter();
    RecyclerView getRecyclerView();
}
