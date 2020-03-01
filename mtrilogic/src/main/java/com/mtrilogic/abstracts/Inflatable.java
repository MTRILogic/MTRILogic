package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class Inflatable<M extends Modelable, VB extends ViewBinding> extends LiveData<M>
        implements Observer<M> {

    protected final InflatableAdapterListener listener;
    protected final View itemView;
    protected int position;
    protected VB binding;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract void onBindHolder(@NonNull Modelable modelable);

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Inflatable(@NonNull VB binding, @NonNull InflatableAdapterListener listener){
        itemView = binding.getRoot();
        this.listener = listener;
        this.binding = binding;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int position){
        this.position = position;
        onBindHolder(modelable);
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
