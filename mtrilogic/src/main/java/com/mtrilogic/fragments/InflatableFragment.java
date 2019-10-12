package com.mtrilogic.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.pages.InflatablePage;
import com.mtrilogic.views.InflatableView;

import java.util.ArrayList;

@SuppressWarnings("unused")
public abstract class InflatableFragment<P extends InflatablePage> extends Fragmentable<P> implements InflatableListener, InflatableAdapterListener {
    private static final String TAG = "InflatableFragmentTAG", STATE = "state";
    private InflatableAdapter adapter;
    private InflatableView lvwItems;
    private static int top, index;

    protected void init(View view, int typeCount){
        ArrayList<Modelable> modelableList = getPage().getModelableList();
        adapter = new InflatableAdapter(this, modelableList, typeCount);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            lvwItems.onRestoreInstanceState(savedInstanceState.getParcelable(STATE));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (lvwItems != null) {
            outState.putParcelable(STATE, lvwItems.onSaveInstanceState());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public InflatableAdapter getInflatableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        FragmentableAdapterListener listener = getListener();
        if (listener != null) {
            listener.onMakeToast(line);
        }
    }
}
