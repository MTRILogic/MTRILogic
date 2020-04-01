package com.mtrilogic.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;

@SuppressWarnings("unused")
public interface RecyclableItemListener extends ItemListener {
    RecyclableAdapter getRecyclableAdapter();
    RecyclerView getRecyclerView();
}
