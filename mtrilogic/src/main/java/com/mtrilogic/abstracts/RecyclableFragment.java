package com.mtrilogic.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.interfaces.RecyclableListener;

@SuppressWarnings({"unused"})
public abstract class RecyclableFragment<P extends ListPaginable<Modelable>, L extends FragmentListener> extends Fragmentable<P, L> implements RecyclableListener, RecyclableItemListener {
    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    // ================< PROTECTED METHODS >========================================================

    protected void bindRecyclable(@NonNull Context context, @NonNull RecyclerView lvwItems, @NonNull P page, @NonNull RecyclerView.LayoutManager manager){
        Listable<Modelable> listable = page.getListable();
        if (listable != null) {
            adapter = new RecyclableAdapter(context, this, listable);
            this.lvwItems = lvwItems;
            lvwItems.setLayoutManager(manager);
            lvwItems.setAdapter(adapter);
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public RecyclerView getRecyclerView() {
        return lvwItems;
    }

    @Override
    public RecyclableAdapter getRecyclableAdapter(){
        return adapter;
    }
}
