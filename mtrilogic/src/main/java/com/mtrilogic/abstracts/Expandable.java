package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;

public abstract class Expandable implements Bindable{
    protected boolean expanded, lastChild;
    protected ExpandableAdapterListener listener;
    protected Context context;

    public abstract View getExpandableView(ViewGroup parent);

    public Expandable(Context context, ExpandableAdapterListener listener){
        this.context = context;
        this.listener = listener;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

    public void setLastChild(boolean lastChild){
        this.lastChild = lastChild;
    }
}