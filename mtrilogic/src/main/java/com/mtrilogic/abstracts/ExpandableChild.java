package com.mtrilogic.abstracts;

import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;

public abstract class ExpandableChild<M extends Modelable> extends Expandable<M>{
    private boolean selectable;

    public ExpandableChild(OnNotifyDataSetChangedListener listener, M model, long id, boolean selectable){
        super(listener,model,id);
        this.selectable = selectable;
    }

    public boolean isSelectable(){
        return selectable;
    }
}