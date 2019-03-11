package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.InflatableAdapterListener;

public abstract class Inflatable implements Bindable{
    private InflatableAdapterListener listener;
    private Context context;

    // +++++++++++++++++| PUBLIC ABSTRACT METHODS |++++++++++++++++++++++++++++

    public abstract View getInflatableView(ViewGroup parent);

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    protected Inflatable(Context context, InflatableAdapterListener listener){
        this.context = context;
        this.listener = listener;
    }

    // +++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return context;
    }

    protected InflatableAdapterListener getListener(){
        return listener;
    }
}
