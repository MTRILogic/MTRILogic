package com.mtrilogic.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public abstract class RecyclableFragment<P extends ListPaginable<Modelable>, VB extends ViewBinding>
        extends Fragmentable<P, VB> implements RecyclableListener, RecyclableAdapterListener {

    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    protected abstract RecyclerView.LayoutManager getLayoutManager(Context context);

    // ================< PROTECTED METHODS >========================================================

    protected void bindRecyclable(@NonNull RecyclerView lvwItems){
        this.lvwItems = lvwItems;
        Context context = getContext();
        ArrayList<Modelable> modelables = page.getListable().getModelableList();
        adapter = new RecyclableAdapter(context,this, modelables);
        RecyclerView.LayoutManager manager = getLayoutManager(context);
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
