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
public abstract class Recyclable<M extends Modelable> extends RecyclerView.ViewHolder
        implements Observer<M> {

    protected final RecyclableAdapterListener listener;
    protected int position;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract void onBindHolder(@NonNull Modelable modelable);

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Recyclable(@NonNull View itemView, @NonNull RecyclableAdapterListener listener){
        super(itemView);
        this.listener = listener;
    }

    public Recyclable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull RecyclableAdapterListener listener){
        super(inflater.inflate(resource, parent, false));
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void bindModel(@NonNull Modelable modelable, int position){
        this.position = position;
        onBindHolder(modelable);
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
