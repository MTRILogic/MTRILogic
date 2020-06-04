package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.InflatableItemListener;

@SuppressWarnings("unused")
public abstract class BindingInflatable<M extends Modelable, L extends InflatableItemListener, VB extends ViewBinding> extends Inflatable<M, L> {
    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingInflatable(@NonNull VB binding, @NonNull L listener) {
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
