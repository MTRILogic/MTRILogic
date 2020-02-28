package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class ExpandableChild <M extends Modelable, VB extends ViewBinding>
        extends LiveData<M> implements Observer<M> {

    protected final ExpandableAdapterListener listener;
    protected final View itemView;
    protected int groupPosition;
    protected int childPosition;
    protected boolean lastChild;
    protected VB binding;
    protected M model;

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    public abstract void onBindHolder(Modelable modelable, int groupPosition, int childPosition,
                                      boolean lastChild);

    // ================< PROTECTED CONSTRUCTORS >===================================================

    public ExpandableChild(@NonNull VB binding, @NonNull ExpandableAdapterListener listener){
        itemView = binding.getRoot();
        this.listener = listener;
        this.binding = binding;
    }

    // ================< PUBLIC METHODS >===========================================================

    public View getItemView() {
        return itemView;
    }

    // ================< PROTECTED METHODS >========================================================

    protected void bindModel(@NonNull M model, int groupPosition, int childPosition, boolean lastChild){
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        this.model = model;
    }

    protected void autoDelete(){
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
