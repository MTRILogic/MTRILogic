package com.mtrilogic.abstracts;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.interfaces.Observable;

@SuppressWarnings("unused")
public abstract class ExpandableGroup<M extends Modelable> extends Expandable<M> {
    protected boolean expanded;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableGroup(@NonNull View itemView, @NonNull ExpandableItemListener listener){
        super(itemView, listener);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void bindModelable(@NonNull Modelable modelable, int groupPosition, boolean expanded){
        model = getModelFromModelable(modelable);
        this.groupPosition = groupPosition;
        this.expanded = expanded;
        if (model != null){
            onBindModel();
        }
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected boolean autoDelete(){
        return listener.getModelableMapable().deleteGroup(model);
    }

    protected boolean addChild(@NonNull Modelable child){
        return listener.getModelableMapable().appendChild(model, child);
    }

    protected long getChildIdx(){
        return listener.getModelableMapable().getChildIdx(model);
    }

    protected void setChildIdx(long idx){
        listener.getModelableMapable().setChildIdx(model, idx);
    }

    protected void detachChildList(@NonNull Observable observable){
        listener.getModelableMapable().detachChildList(model, observable);
    }

    protected void autoExpanded(){
        ExpandableListView lvwItems = listener.getExpandableListView();
        if (!lvwItems.isGroupExpanded(groupPosition)){
            lvwItems.expandGroup(groupPosition, true);
        }
    }
}
