package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.views.InflatableView;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public abstract class InflatableFragment<P extends ListPaginable<Modelable>, VB extends ViewBinding>
        extends Fragmentable<P, VB> implements InflatableListener, InflatableAdapterListener {

    protected InflatableAdapter adapter;
    protected InflatableView lvwItems;

    // ================< PROTECTED METHODS >========================================================

    protected void bindInflatable(@NonNull InflatableView lvwItems, int typeCount){
        this.lvwItems = lvwItems;
        Context context = getContext();
        ArrayList<Modelable> modelableList = page.getListable().getModelableList();
        adapter = new InflatableAdapter(context,this, modelableList, typeCount);
        lvwItems.setAdapter(adapter);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (args != null && lvwItems != null){
            lvwItems.restoreFromState(args);
        }
    }

    @Override
    public void onPause() {
        if (args != null && lvwItems != null) {
            lvwItems.saveToState(args);
        }
        super.onPause();
    }

    @Override
    public void onNewPosition(int position) {

    }

    @Override
    public InflatableView getInflatableView() {
        return lvwItems;
    }

    @Override
    public InflatableAdapter getInflatableAdapter(){
        return adapter;
    }
}
