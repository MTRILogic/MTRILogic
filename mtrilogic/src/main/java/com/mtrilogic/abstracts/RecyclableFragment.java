package com.mtrilogic.abstracts;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;
import com.mtrilogic.mtrilogic.R;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class RecyclableFragment<P extends ListablePage> extends Fragmentable<P>
        implements RecyclableListener, RecyclableAdapterListener {
    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    protected abstract void onRecyclableCreated();

// ****************| PROTECTED METHODS |************************************************************

    protected void initRecyclable(View view, RecyclerView.LayoutManager layoutManager){
        ArrayList<Modelable> modelables = page.getModelableList();
        adapter = new RecyclableAdapter(this, modelables);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setLayoutManager(layoutManager);
        lvwItems.setAdapter(adapter);
        onRecyclableCreated();
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onNewPosition() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return lvwItems;
    }

    @Override
    public RecyclableAdapter getRecyclableAdapter(){
        return adapter;
    }
}
