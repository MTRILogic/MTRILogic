package com.mtrilogic.abstracts.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.abstracts.pages.ListablePage;

import java.util.ArrayList;

@SuppressWarnings("unused")
public abstract class InflatableFragment<P extends ListablePage> extends Fragmentable<P> implements
        InflatableListener, InflatableAdapterListener {
    private static final String TAG = "InflatableFragmentTAG", INDEX = "index", TOP = "top";
    private InflatableAdapter adapter;
    private ListView lvwItems;

// ****************| PROTECTED METHODS |************************************************************

    protected void init(View view, int typeCount, P page){
        ArrayList<Modelable> modelableList = page.getModelableList();
        adapter = new InflatableAdapter(this, modelableList, typeCount);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
    }

// ****************| PUBLIC OVERRIDE METHODS |******************************************************

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (state != null && lvwItems != null){
            int index = state.getInt(INDEX, Base.INVALID_POSITION);
            if (index == 0) {
                int top = state.getInt(TOP);
                lvwItems.setSelectionFromTop(index, top);
            }
        }
    }

    @Override
    public void onPause() {
        if (state != null && lvwItems != null) {
            int index = lvwItems.getFirstVisiblePosition();
            if (index == 0){
                View view = lvwItems.getChildAt(0);
                int top = view != null ? view.getTop() - lvwItems.getPaddingTop() : 0;
                state.putInt(INDEX, index);
                state.putInt(TOP, top);
            }
        }
        super.onPause();
    }

    @Override
    public InflatableAdapter getInflatableAdapter(){
        return adapter;
    }
}
