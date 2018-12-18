package com.mtri.abstracts;

import com.mtri.interfaces.OnNotifyDataSetChangedListener;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableGroup<M extends Modelable> extends Expandable<M>{
    private List<ExpandableChild<?>> childList;

    public ExpandableGroup(OnNotifyDataSetChangedListener listener, M model, long id){
        super(listener,model,id);
        childList = new ArrayList<>();
    }

    public List<ExpandableChild<?>> getChildList(){
        return childList;
    }
}