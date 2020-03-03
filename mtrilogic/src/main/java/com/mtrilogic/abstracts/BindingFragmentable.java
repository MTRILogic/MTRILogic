package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

@SuppressWarnings("unused")
public abstract class BindingFragmentable<P extends Paginable, VB extends ViewBinding> extends Fragmentable<P> {

    protected VB binding;
}
