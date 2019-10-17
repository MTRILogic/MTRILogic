package com.mtrilogic.abstracts.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.abstracts.pages.MapablePage;

@SuppressWarnings("unused")
public abstract class ExpandableFragment<P extends MapablePage> extends Fragmentable<P> implements ExpandableListener, ExpandableAdapterListener {
    private static final String TAG = "ExpandableFragment", INDEX = "index", TOP = "top";
    private ExpandableAdapter adapter;
    private ExpandableListView lvwItems;

// ****************| PROTECTED METHODS |************************************************************

    protected void init(View view, int groupTypeCount, int childTypeCount, P page){
        Listable<Modelable> groupListable = page.getGroupListable();
        Mapable<Modelable> childMapable = page.getChildMapable();
        adapter = new ExpandableAdapter(this, groupListable, childMapable, groupTypeCount, childTypeCount);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
    }

// ****************| PUBLIC OVERRIDE METHODS |******************************************************

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && lvwItems != null){
            int index = savedInstanceState.getInt(INDEX, Base.INVALID_POSITION);
            if (index == 0) {
                int top = savedInstanceState.getInt(TOP);
                lvwItems.setSelectionFromTop(index, top);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (lvwItems != null){
            int index = lvwItems.getFirstVisiblePosition();
            if (index == 0){
                View view = lvwItems.getChildAt(0);
                int top = view != null ? view.getTop() - lvwItems.getPaddingTop() : 0;
                outState.putInt(INDEX, index);
                outState.putInt(TOP, top);
            }
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }
}
