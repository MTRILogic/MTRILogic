package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class Recyclable<M extends Modelable, VB extends ViewBinding>
        extends RecyclerView.ViewHolder implements Observer<M> {

    protected final RecyclableAdapterListener listener;
    protected int position;
    protected VB binding;
    protected M model;

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    public abstract void onBindHolder(Modelable modelable, int position);

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Recyclable(@NonNull VB binding, @NonNull RecyclableAdapterListener listener){
        super(binding.getRoot());
        this.listener = listener;
        this.binding = binding;
    }

    // ================< PROTECTED METHODS >========================================================

    protected void bindModel(@NonNull M model, int position){
        this.position = position;
        this.model = model;
    }

    protected void autoDelete(){
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
