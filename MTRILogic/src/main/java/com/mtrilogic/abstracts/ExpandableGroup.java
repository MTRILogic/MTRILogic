package com.mtrilogic.abstracts;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.interfaces.Observable;

@SuppressWarnings("unused")
public abstract class ExpandableGroup<M extends Modelable> implements Bindable<M> {
    protected final ExpandableItemListener listener;
    protected final View itemView;

    protected int groupPosition;
    protected boolean expanded;
    protected M model;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableGroup(@NonNull View itemView, @NonNull ExpandableItemListener listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    @NonNull
    public View getItemView(){
        onBindItemView();
        return itemView;
    }

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

    protected void notifyChanged(){
        listener.getExpandableAdapter().notifyDataSetChanged();
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

    protected void makeToast(String line){
        listener.onMakeToast(line);
    }

    protected void makeLog(String line){
        listener.onMakeLog(line);
    }
}
