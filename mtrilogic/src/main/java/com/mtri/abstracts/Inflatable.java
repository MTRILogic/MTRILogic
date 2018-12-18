package com.mtri.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtri.interfaces.OnNotifyDataSetChangedListener;

public abstract class Inflatable<M extends Modelable>{
    protected OnNotifyDataSetChangedListener listener;
    protected M model;
    protected long id;

    public abstract View getInflatableView(View view, ViewGroup parent, Context context);

    public Inflatable(OnNotifyDataSetChangedListener listener, M model, long id){
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
