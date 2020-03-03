package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

@SuppressWarnings("unused")
public abstract class BindingInflatableFragment<P extends ListPaginable<Modelable>, VB extends ViewBinding>
        extends InflatableFragment<P> {

    protected VB binding;
}
