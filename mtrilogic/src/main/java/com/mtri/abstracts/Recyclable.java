package com.mtri.abstracts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.mtri.interfaces.OnAdapterChangedListener;

public abstract class Recyclable<VH extends RecyclerView.ViewHolder, M extends Modelable>{
    protected OnAdapterChangedListener listener;
    private long id;

    public abstract void onBindHolder(VH holder, Context context);
    public abstract int getViewType();
    public abstract M getModel();

    public Recyclable(OnAdapterChangedListener listener, long id){
        this.listener = listener;
        this.id = id;
    }

    public long getItemId(){
        return id;
    }

    public <T extends VH> void bindViewHolder(T holder,Context context){
        onBindHolder(holder,context);
    }
}