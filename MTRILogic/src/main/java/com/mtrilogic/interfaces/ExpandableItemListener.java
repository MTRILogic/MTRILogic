package com.mtrilogic.interfaces;

import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Mapable;

@SuppressWarnings("unused")
public interface ExpandableItemListener extends OnMakeToastListener {
    @NonNull
    Mapable<Modelable> getModelableMapable();
    @NonNull
    ExpandableAdapter getExpandableAdapter();
    @NonNull
    ExpandableListView getExpandableListView();
}