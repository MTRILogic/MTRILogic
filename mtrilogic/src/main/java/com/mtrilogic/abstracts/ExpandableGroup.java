package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExpandableGroup<M extends Modelable, L extends ExpandableItemListener>
        extends LiveData<M> implements Observer<M> {
    protected final View itemView;
    protected final L listener;

    protected int groupPosition;
    protected boolean expanded;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract M getModelFromModelable(@NonNull Modelable modelable);
    protected abstract void onBindModel();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableGroup(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public ExpandableGroup(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                           @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        if (itemView != null){
            onBindItemView();
        }
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindHolder(@NonNull Modelable modelable, int groupPosition, boolean expanded){
        model = getModelFromModelable(modelable);
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        this.groupPosition = groupPosition;
        this.expanded = expanded;
        if (model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected void onBindItemView(){

    }

    protected final void autoDelete(){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if (adapter != null && adapter.deleteGroupModelable(model)){
            adapter.notifyDataSetChanged();
        }
    }

    protected final void addNewChildModelable(@NonNull Modelable childModelable){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if (adapter != null && adapter.appendChildModelable(model, childModelable)) {
            adapter.notifyDataSetChanged();
            if (adapter.getChildrenCount(groupPosition) == 1){
                ExpandableView lvwItems = listener.getExpandableView();
                if (lvwItems != null && !lvwItems.isGroupExpanded(groupPosition)){
                    lvwItems.expandGroup(groupPosition);
                }
            }
        }
    }
}
