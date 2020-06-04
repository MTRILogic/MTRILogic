package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.ExpandableItemListener;

@SuppressWarnings({"unused"})
public abstract class BindingExpandableChild<M extends Modelable, L extends ExpandableItemListener, VB extends ViewBinding> extends ExpandableChild<M, L> {
    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingExpandableChild(@NonNull VB binding, @NonNull L listener){
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
