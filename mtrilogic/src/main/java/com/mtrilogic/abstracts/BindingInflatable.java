package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.InflatableAdapterListener;

@SuppressWarnings("unused")
public abstract class BindingInflatable<M extends Modelable, VB extends ViewBinding> extends Inflatable<M> {

    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingInflatable(@NonNull VB binding, @NonNull InflatableAdapterListener listener) {
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
