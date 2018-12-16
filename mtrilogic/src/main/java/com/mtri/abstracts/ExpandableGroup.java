package com.mtri.abstracts;

import com.mtri.interfaces.OnNotifyDataSetChangedListener;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableGroup extends Expandable{
    private List<ExpandableChild> childList;

    public ExpandableGroup(OnNotifyDataSetChangedListener listener, long id){
        super(listener,id);
        childList = new ArrayList<>();
    }

    public List<ExpandableChild> getChildList(){
        return childList;
    }
}