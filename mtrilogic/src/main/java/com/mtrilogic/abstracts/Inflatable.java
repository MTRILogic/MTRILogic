package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableItemListener;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Inflatable<M extends Modelable, L extends InflatableItemListener>
        extends LiveData<M> implements Observer<M> {
    protected final View itemView;
    protected final L listener;

    protected int position;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract M getModelFromModelable(@NonNull Modelable modelable);
    protected abstract void onBindModel();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Inflatable(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public Inflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindHolder(@NonNull Modelable modelable, int position){
        model = getModelFromModelable(modelable);
        this.position = position;
        if (model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected void onBindItemView(){

    }

    protected final void autoDelete(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
