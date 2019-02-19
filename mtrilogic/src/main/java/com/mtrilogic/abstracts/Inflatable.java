package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.InflatableAdapterListener;

public abstract class Inflatable implements Bindable{
    protected InflatableAdapterListener listener;
    protected Context context;

    public abstract View getInflatableView(ViewGroup parent);

    protected Inflatable(Context context, InflatableAdapterListener listener){
        this.context = context;
        this.listener = listener;
    }
}
