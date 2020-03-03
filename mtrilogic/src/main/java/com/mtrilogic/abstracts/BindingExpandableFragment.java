package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

@SuppressWarnings("unused")
public abstract class BindingExpandableFragment<P extends MapPaginable<Modelable>, VB extends ViewBinding>
        extends ExpandableFragment<P> {

    protected VB binding;
}
