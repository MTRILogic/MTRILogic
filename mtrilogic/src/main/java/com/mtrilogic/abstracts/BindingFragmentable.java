package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings("unused")
public abstract class BindingFragmentable<P extends Paginable, L extends FragmentableAdapterListener,
        VB extends ViewBinding> extends Fragmentable<P, L> {
    protected VB binding;
}
