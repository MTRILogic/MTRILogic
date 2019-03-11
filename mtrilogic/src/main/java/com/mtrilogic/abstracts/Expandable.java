package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;

public abstract class Expandable implements Bindable{
    private boolean expanded, lastChild;
    private ExpandableAdapterListener listener;
    private Context context;

    // +++++++++++++++++| PUBLIC ABSTRACT METHODS |++++++++++++++++++++++++++++

    public abstract View getExpandableView(ViewGroup parent);

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public Expandable(Context context, ExpandableAdapterListener listener){
        this.context = context;
        this.listener = listener;
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

    public void setLastChild(boolean lastChild){
        this.lastChild = lastChild;
    }

    // +++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++

    protected boolean isExpanded(){
        return expanded;
    }

    protected boolean isLastChild(){
        return lastChild;
    }

    protected Context getContext(){
        return context;
    }

    protected ExpandableAdapterListener getListener(){
        return listener;
    }
}