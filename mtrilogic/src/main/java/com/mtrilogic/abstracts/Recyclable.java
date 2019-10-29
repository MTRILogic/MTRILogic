package com.mtrilogic.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Recyclable<M extends Modelable> extends RecyclerView.ViewHolder implements Observer<M> {
    protected final OnMakeToastListener listener;
    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;
    protected Context context;
    protected int position;
    protected M model;

// ++++++++++++++++| PROTECTED ABSTRACT METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    protected abstract M getModel(Modelable modelable);

    protected abstract void onBindHolder();

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Recyclable(@NonNull Context context, int resource, @NonNull ViewGroup parent,
                      @NonNull RecyclableAdapterListener listener){
        super(LayoutInflater.from(context).inflate(resource, parent, false));
        this.context = context;
        this.listener = listener;
        adapter = listener.getRecyclableAdapter();
        lvwItems = listener.getRecyclerView();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
