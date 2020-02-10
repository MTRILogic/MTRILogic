package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class ExpandableChild <M extends Modelable> extends LiveData<M> implements Observer<M> {
    protected final OnMakeToastListener listener;
    protected final View itemView;
    protected ExpandableAdapter adapter;
    protected ExpandableView lvwItems;
    protected int groupPosition;
    protected int childPosition;
    protected boolean lastChild;
    protected M model;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected abstract M getModel(Modelable modelable);

    protected abstract void onBindHolder();

// ++++++++++++++++| PROTECTED CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ExpandableChild(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                           @NonNull ExpandableAdapterListener listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        adapter = listener.getExpandableAdapter();
        lvwItems = listener.getExpandableView();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public View getItemView(){
        return itemView;
    }

    public void bindHolder(Modelable modelable, int groupPosition, int childPosition, boolean lastChild){
        model = getModel(modelable);
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        onBindHolder();
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected Context getContext(){
        return itemView.getContext();
    }

    protected void autoDelete(){
        if (adapter != null){
            if (adapter.deleteChildModelable(adapter.getGroupModelable(groupPosition), model)){
                adapter.notifyDataSetChanged();
                if (adapter.getChildrenCount(groupPosition) == 0){
                    if (lvwItems != null && lvwItems.isGroupExpanded(groupPosition)) {
                        lvwItems.collapseGroup(groupPosition);
                    }
                }
            }
        }
    }
}
