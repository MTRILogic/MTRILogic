package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.ExpandableAdapterListener;

@SuppressWarnings("unused")
public abstract class ExpandableGroup{
    protected final ExpandableAdapterListener listener;
    protected final View itemView;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public abstract void onBindHolder(Modelable modelable, int groupPosition, boolean expanded);

// ++++++++++++++++| PROTECTED CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ExpandableGroup(Context context, int resource, ViewGroup parent, ExpandableAdapterListener listener){
        itemView = LayoutInflater.from(context).inflate(resource, parent, false);
        this.listener = listener;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public View getItemView() {
        return itemView;
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return itemView.getContext();
    }
}
