package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;

@SuppressWarnings("unused")
public abstract class Expandable<M extends Modelable> implements Bindable<M> {
    protected final ExpandableItemListener listener;
    protected final View itemView;

    protected int groupPosition;
    protected M model;

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected Expandable(@NonNull View itemView, @NonNull ExpandableItemListener listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    @NonNull
    public View getItemView(){
        onBindItemView();
        return itemView;
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void notifyChanged(){
        listener.getExpandableAdapter().notifyDataSetChanged();
    }

    protected void makeToast(String line){
        listener.onMakeToast(line);
    }

    protected void makeLog(String line){
        listener.onMakeLog(line);
    }
}
