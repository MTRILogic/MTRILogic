package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings("unused")
public abstract class BindingInflatableFragment<P extends ListPaginable<Modelable>,
        L extends FragmentableAdapterListener,
        VB extends ViewBinding> extends InflatableFragment<P, L> {
    protected VB binding;
}
