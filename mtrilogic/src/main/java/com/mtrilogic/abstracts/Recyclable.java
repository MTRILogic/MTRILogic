package com.mtrilogic.abstracts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;

public abstract class Recyclable<VH extends RecyclerView.ViewHolder, M extends Modelable>{
    protected OnNotifyDataSetChangedListener listener;
    protected long id;

    public abstract void onBindHolder(VH holder, Context context);
    public abstract M getModel();

    public Recyclable(OnNotifyDataSetChangedListener listener, long id){
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