package com.mtrilogic.interfaces;

import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Mappable;

@SuppressWarnings("unused")
public interface ExpandableItemListener extends ItemListener, ExpandableListener{
    @NonNull
    Mappable<Model> getModelMappable();

    @NonNull
    ExpandableAdapter getExpandableAdapter();

    @NonNull
    ExpandableListView getExpandableListView();
}