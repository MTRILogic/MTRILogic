package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.InflatableItemListener;

@SuppressWarnings("unused")
public abstract class Inflatable<M extends Modelable> implements Bindable<M> {
    protected final InflatableItemListener listener;
    protected final View itemView;

    protected int position;
    protected M model;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public Inflatable(@NonNull View itemView, @NonNull InflatableItemListener listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public View getItemView() {
        onBindItemView();
        return itemView;
    }

    public void bindModelable(@NonNull Modelable modelable, int position){
        model = getModelFromModelable(modelable);
        this.position = position;
        if (model != null){
            onBindModel();
        }
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected boolean autoDelete(){
        return listener.getModelableListable().delete(model);
    }

    protected void notifyChanged(){
        listener.getInflatableAdapter().notifyDataSetChanged();
    }

    protected void makeToast(String line){
        listener.onMakeToast(line);
    }

    protected void makeLog(String line){
        listener.onMakeLog(line);
    }
}
