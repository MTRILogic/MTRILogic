package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface RecyclableItemListener extends ItemListener, RecyclableListener{

    @NonNull
    Listable<Model> getModelListable();

    @NonNull
    RecyclableAdapter getRecyclableAdapter();

    @NonNull
    RecyclerView getRecyclerView();
}
