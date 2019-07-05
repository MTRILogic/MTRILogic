package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.ExpandableAdapterListener;

public abstract class ExpandableGroup{
    private ExpandableAdapterListener listener;
    private Context context;
    private int resource;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |+++++++++++++++++++++++++++++++++

    public abstract View getInflatableView(ViewGroup parent);

    public abstract void onBindHolder(Modelable modelable, int groupPosition, boolean expanded);

// ++++++++++++++++| PROTECTED CONSTRUCTORS |++++++++++++++++++++++++++++++++++

    public ExpandableGroup(Context context, ExpandableAdapterListener listener, int resource){
        this.context = context;
        this.listener = listener;
        this.resource = resource;
    }

// ++++++++++++++++| PROTECTED METHODS |+++++++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return context;
    }

    protected ExpandableAdapterListener getListener(){
        return listener;
    }

    protected int getLayoutResource(){
        return resource;
    }
}
