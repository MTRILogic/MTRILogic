package com.mtrilogic.abstracts;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.RecyclableAdapterListener;

public abstract class Recyclable extends RecyclerView.ViewHolder{
    private RecyclableAdapterListener listener;
    private Context context;
    private int resource;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |+++++++++++++++++++++++++++++++++

    public abstract Recyclable getRecyclableHolder(ViewGroup parent);

    public abstract void onBindHolder(Modelable modelable, int position);

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public Recyclable(@NonNull View view, Context context, RecyclableAdapterListener listener, int resource){
        super(view);
        this.context = context;
        this.listener = listener;
        this.resource = resource;
    }

// ++++++++++++++++| PROTECTED METHODS |+++++++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return context;
    }

    protected RecyclableAdapterListener getListener(){
        return listener;
    }

    protected int getLayoutResource(){
        return resource;
    }
}