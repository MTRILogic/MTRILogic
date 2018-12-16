package com.mtri.abstracts;

import com.mtri.interfaces.OnNotifyDataSetChangedListener;

public abstract class ExpandableChild extends Expandable{
    private boolean selectable;

    public ExpandableChild(OnNotifyDataSetChangedListener listener, long id, boolean selectable){
        super(listener,id);
        this.selectable = selectable;
    }

    public boolean isSelectable(){
        return selectable;
    }
}