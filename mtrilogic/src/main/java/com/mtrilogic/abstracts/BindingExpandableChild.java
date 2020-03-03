package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.ExpandableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class BindingExpandableChild<M extends Modelable, VB extends ViewBinding>
        extends ExpandableChild<M> {

    protected VB binding;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public BindingExpandableChild(@NonNull VB binding, @NonNull ExpandableAdapterListener listener){
        super(binding.getRoot(), listener);
        this.binding = binding;
    }
}
