package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.RecyclableAdapterListener;

@SuppressWarnings("unused")
public abstract class BindingRecyclable<M extends Modelable, L extends RecyclableAdapterListener,
        VB extends ViewBinding> extends Recyclable<M, L> {
    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingRecyclable(@NonNull VB binding, @NonNull L listener){
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
