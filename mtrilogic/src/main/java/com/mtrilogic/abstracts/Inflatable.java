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
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.views.InflatableView;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Inflatable<M extends Modelable> extends LiveData<M> implements Observer<M> {
    protected final OnMakeToastListener listener;
    protected final View itemView;
    protected InflatableAdapter adapter;
    protected InflatableView lvwItems;
    protected Context context;
    protected int position;
    protected M model;

// ++++++++++++++++| PROTECTED ABSTRACT METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    protected abstract M getModel(Modelable modelable);

    protected abstract void onBindHolder();

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Inflatable(@NonNull Context context, int resource, @NonNull ViewGroup parent,
                      @NonNull InflatableAdapterListener listener){
        itemView = LayoutInflater.from(context).inflate(resource, parent, false);
        this.context = context;
        this.listener = listener;
        adapter = listener.getInflatableAdapter();
        lvwItems = listener.getInflatableView();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public View getItemView(){
        return itemView;
    }

    public void bindHolder(Modelable modelable, int position){
        model = getModel(modelable);
        this.position = position;
        onBindHolder();
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected void autoDelete(){
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
