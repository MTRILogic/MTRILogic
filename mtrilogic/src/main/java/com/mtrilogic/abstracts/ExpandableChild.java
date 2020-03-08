package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExpandableChild <M extends Modelable, L extends ExpandableAdapterListener>
        extends LiveData<M> implements Observer<M> {
    protected final View itemView;
    protected final L listener;

    protected int groupPosition;
    protected int childPosition;
    protected boolean lastChild;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract M getModelFromModelable(@NonNull Modelable modelable);
    protected abstract void onBindHolder();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableChild(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public ExpandableChild(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                           @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindHolder(@NonNull Modelable modelable, int groupPosition, int childPosition,
                                boolean lastChild){
        model = getModelFromModelable(modelable);
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        if (model != null) {
            onBindHolder();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if (adapter != null){
            if (adapter.deleteChildModelable(adapter.getGroupModelable(groupPosition), model)){
                adapter.notifyDataSetChanged();
                if (adapter.getChildrenCount(groupPosition) == 0){
                    ExpandableView lvwItems = listener.getExpandableView();
                    if (lvwItems != null && lvwItems.isGroupExpanded(groupPosition)) {
                        lvwItems.collapseGroup(groupPosition);
                    }
                }
            }
        }
    }
}
