package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.ExpandableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class BindingExpandableGroup<M extends Modelable, L extends ExpandableAdapterListener,
        VB extends ViewBinding> extends ExpandableGroup<M, L> {
    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingExpandableGroup(@NonNull VB binding, @NonNull L listener){
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
