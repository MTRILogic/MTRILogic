package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class Inflatable<M extends Modelable> extends LiveData<M> implements Observer<M> {
    protected final InflatableAdapterListener listener;
    protected final View itemView;
    protected int position;
    protected M model;

// ++++++++++++++++| PROTECTED ABSTRACT METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    protected abstract M getModel(Modelable modelable);

    protected abstract void onBindHolder();

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Inflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull InflatableAdapterListener listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @NonNull
    public View getItemView(){
        return itemView;
    }

    public void bindHolder(@NonNull Modelable modelable, int position){
        model = getModel(modelable);
        this.position = position;
        onBindHolder();
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @NonNull
    protected Context getContext(){
        return itemView.getContext();
    }

    protected void autoDelete(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
