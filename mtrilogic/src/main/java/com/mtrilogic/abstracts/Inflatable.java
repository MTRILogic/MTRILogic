package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.InflatableAdapterListener;

public abstract class Inflatable{
    private InflatableAdapterListener listener;
    private Context context;
    private int resource;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |+++++++++++++++++++++++++++++++++

    public abstract View getInflatableView(ViewGroup parent);

    public abstract void onBindHolder(Modelable modelable, int position);

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public Inflatable(Context context, InflatableAdapterListener listener, int resource){
        this.listener = listener;
        this.context = context;
        this.resource = resource;
    }

// ++++++++++++++++| PROTECTED METHODS |+++++++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return context;
    }

    protected InflatableAdapterListener getListener(){
        return listener;
    }

    protected int getLayoutResource(){
        return resource;
    }
}
