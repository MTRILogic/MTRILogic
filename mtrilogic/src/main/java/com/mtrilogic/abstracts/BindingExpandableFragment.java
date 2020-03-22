package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.FragmentListener;

@SuppressWarnings("unused")
public abstract class BindingExpandableFragment<P extends MapPaginable<Modelable>, L extends FragmentListener,
        VB extends ViewBinding> extends ExpandableFragment<P, L> {
    protected VB binding;
}
