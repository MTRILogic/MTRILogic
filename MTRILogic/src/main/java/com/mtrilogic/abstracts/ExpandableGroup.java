package com.mtrilogic.abstracts;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.interfaces.Observable;

@SuppressWarnings("unused")
public abstract class ExpandableGroup<M extends Model> extends Expandable<M> {
    protected boolean expanded;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableGroup(@NonNull Class<M> clazz, @NonNull View itemView, @NonNull ExpandableItemListener listener){
        super(clazz, itemView, listener);
    }

    /*==============================================================================================
    PUBLIC METHOD
    ==============================================================================================*/

    @NonNull
    @Override
    public View getItemView() {
        itemView.setOnClickListener(v -> listener.onExpandableGroupClick(itemView, model, groupPosition, expanded));
        itemView.setOnLongClickListener(v -> listener.onExpandableGroupLongClick(itemView, model, groupPosition, expanded));
        return super.getItemView();
    }

    public void bindModelable(@NonNull Model model, int groupPosition, boolean expanded){
        this.expanded = expanded;
        super.bindModel(model, groupPosition);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected boolean autoDelete(){
        return listener.getModelMappable().deleteGroup(model);
    }

    protected boolean addChild(@NonNull Model child){
        return listener.getModelMappable().appendChild(model, child);
    }

    protected long getChildIdx(){
        return listener.getModelMappable().getChildIdx(model);
    }

    protected void setChildIdx(long idx){
        listener.getModelMappable().setChildIdx(model, idx);
    }

    protected void detachChildList(@NonNull Observable observable){
        listener.getModelMappable().detachChildList(model, observable);
    }

    protected void autoExpanded(){
        ExpandableListView lvwItems = listener.getExpandableListView();
        if (!lvwItems.isGroupExpanded(groupPosition)){
            lvwItems.expandGroup(groupPosition, true);
        }
    }
}
