package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

import com.mtrilogic.interfaces.FragmentListener;

@SuppressWarnings("unused")
public abstract class BindingFragmentable<P extends Paginable, L extends FragmentListener, VB extends ViewBinding> extends Fragmentable<P, L> {
    protected VB binding;
}
