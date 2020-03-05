package com.mtrilogic.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public abstract class RecyclableFragment<P extends ListPaginable<Modelable>>
        extends Fragmentable<P> implements RecyclableListener, RecyclableAdapterListener {

    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    // ================< PROTECTED METHODS >========================================================

    protected void bindRecyclable(@NonNull Context context, @NonNull RecyclerView lvwItems,
                                  @NonNull RecyclerView.LayoutManager manager){
        this.lvwItems = lvwItems;
        ArrayList<Modelable> modelableList = page.getListable().getModelableList();
        adapter = new RecyclableAdapter(context, this, modelableList);
        lvwItems.setLayoutManager(manager);
        lvwItems.setAdapter(adapter);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onNewPosition(int position) {

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
