package com.mtri.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtri.interfaces.OnNotifyDataSetChangedListener;

public abstract class Expandable{
    protected OnNotifyDataSetChangedListener listener;
    private long id;

    public abstract View getExpandableView(boolean b, View view, ViewGroup parent, Context context);
    public abstract Object getModel();

    Expandable(OnNotifyDataSetChangedListener listener,long id){
        this.listener = listener;
        this.id = id;
    }

    public long getItemId(){
        return id;
    }
}