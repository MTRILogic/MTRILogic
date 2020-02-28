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

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract M getModel(Modelable modelable);
    protected abstract void onBindHolder();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Recyclable(@NonNull VB binding, @NonNull RecyclableAdapterListener listener){
        super(binding.getRoot());
        this.listener = listener;
        this.binding = binding;
    }

    // ================< PUBLIC METHODS >===========================================================

    public void bindHolder(@NonNull Modelable modelable, int position){
        model = getModel(modelable);
        this.position = position;
        onBindHolder();
    }

    // ================< PROTECTED METHODS >========================================================

    protected void autoDelete(){
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
