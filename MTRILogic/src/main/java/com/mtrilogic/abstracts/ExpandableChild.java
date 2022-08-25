package com.mtrilogic.abstracts;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;

@SuppressWarnings("unused")
public abstract class ExpandableChild<M extends Modelable> implements Bindable<M> {
    protected final ExpandableItemListener listener;
    protected final View itemView;

    protected int groupPosition, childPosition;
    protected boolean lastChild;
    protected M model;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableChild(@NonNull View itemView, @NonNull ExpandableItemListener listener){
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

    public void bindModelable(@NonNull Modelable modelable, int groupPosition, int childPosition, boolean lastChild){
        model = getModelFromModelable(modelable);
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        if (model != null) {
            onBindModel();
        }
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected Modelable getGroup(){
        return listener.getModelableMapable().getGroup(groupPosition);
    }

    protected boolean autoDelete(@NonNull Modelable group){
        return listener.getModelableMapable().deleteChild(group, model);
    }

    protected void notifyChanged(){
        listener.getExpandableAdapter().notifyDataSetChanged();
    }

    protected void autoCollapse(@NonNull Modelable group){
        if (listener.getModelableMapable().getChildCount(group) == 0){
            ExpandableListView lvwItems = listener.getExpandableListView();
            if (lvwItems.isGroupExpanded(groupPosition)){
                lvwItems.collapseGroup(groupPosition);
            }
        }
    }

    protected void makeToast(String line){
        listener.onMakeToast(line);
    }

    protected void makeLog(String line){
        listener.onMakeLog(line);
    }
}
