package com.mtrilogic.abstracts;

import androidx.viewbinding.ViewBinding;

@SuppressWarnings("unused")
public abstract class BindingRecyclableFragment<P extends ListPaginable<Modelable>, VB extends ViewBinding>
        extends RecyclableFragment<P> {

    protected VB binding;
}
