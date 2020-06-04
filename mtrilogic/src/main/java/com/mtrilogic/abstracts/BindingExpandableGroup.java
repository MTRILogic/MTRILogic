package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.ExpandableItemListener;

@SuppressWarnings({"unused"})
public abstract class BindingExpandableGroup<M extends Modelable, L extends ExpandableItemListener, VB extends ViewBinding> extends ExpandableGroup<M, L> {
    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingExpandableGroup(@NonNull VB binding, @NonNull L listener){
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
