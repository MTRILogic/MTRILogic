package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.views.InflatableView;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class InflatableFragment<P extends ListablePage> extends Fragmentable<P>
        implements InflatableListener, InflatableAdapterListener {
    protected InflatableAdapter adapter;
    protected InflatableView lvwItems;

    // ++++++++++++++++| PROTECTED ABSTRACT METHODS |+++++++++++++++++++++++++++++++++++++++++++++++

    protected abstract void onInflatableCreated();

    // ****************| PROTECTED METHODS |********************************************************

    protected void initInflatable(@NonNull View view, int typeCount){
        Context context = getContext();
        ArrayList<Modelable> modelableList = page.getModelableList();
        adapter = new InflatableAdapter(context,this, modelableList, typeCount);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        onInflatableCreated();
    }

    // ****************| PUBLIC OVERRIDE METHODS |**************************************************

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
