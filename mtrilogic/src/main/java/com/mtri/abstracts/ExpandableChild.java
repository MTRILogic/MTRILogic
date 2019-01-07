package com.mtri.abstracts;

import com.mtri.interfaces.OnAdapterChangedListener;

public abstract class ExpandableChild<M extends Modelable> extends Expandable<M>{
    private boolean selectable;

    public ExpandableChild(OnAdapterChangedListener listener, M model, long id, boolean selectable){
        super(listener,model,id);
        this.selectable = selectable;
    }

    public boolean isSelectable(){
        return selectable;
    }
}