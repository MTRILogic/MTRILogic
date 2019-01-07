package com.mtri.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtri.interfaces.OnAdapterChangedListener;

public abstract class Inflatable<M extends Modelable>{
    protected OnAdapterChangedListener listener;
    protected long id;

    public abstract View getInflatableView(View view, ViewGroup parent, Context context);
    public abstract int getViewType();
    public abstract M getModel();

    public Inflatable(OnAdapterChangedListener listener, long id){
        this.listener = listener;
        this.id = id;
    }

    public long getItemId(){
        return id;
    }
}
