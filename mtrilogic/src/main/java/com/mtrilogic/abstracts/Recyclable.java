package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class Recyclable<M extends Modelable, L extends RecyclableAdapterListener>
        extends RecyclerView.ViewHolder implements Observer<M> {
    protected final L listener;

    protected int position;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract M getModelFromModelable(@NonNull Modelable modelable);
    protected abstract void onBindHolder();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Recyclable(@NonNull View itemView, @NonNull L listener){
        super(itemView);
        this.listener = listener;
    }

    public Recyclable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull L listener){
        super(inflater.inflate(resource, parent, false));
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void bindHolder(@NonNull Modelable modelable, int position){
        model = getModelFromModelable(modelable);
        this.position = position;
        onBindHolder();
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
