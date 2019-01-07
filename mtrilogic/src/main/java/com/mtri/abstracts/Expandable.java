package com.mtri.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtri.interfaces.OnAdapterChangedListener;

public abstract class Expandable<M extends Modelable>{
    protected OnAdapterChangedListener listener;
    protected M model;
    protected long id;

    public abstract View getExpandableView(boolean b, View view, ViewGroup parent, Context context);

    Expandable(OnAdapterChangedListener listener, M model, long id){
        this.listener = listener;
        this.model = model;
        this.id = id;
    }

    public M getModel(){
        return model;
    }

    public long getItemId(){
        return id;
    }
}