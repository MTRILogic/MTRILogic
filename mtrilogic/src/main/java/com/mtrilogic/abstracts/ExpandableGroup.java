package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExpandableGroup<M extends Modelable, L extends ExpandableItemListener> implements Bindable<M> {
    protected final View itemView;
    protected final L listener;
    protected int groupPosition;
    protected boolean expanded;
    protected M model;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableGroup(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public ExpandableGroup(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int groupPosition, boolean expanded){
        this.model = getModelFromModelable(modelable);
        this.groupPosition = groupPosition;
        this.expanded = expanded;
        if (this.model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final M getModel() {
        return model;
    }

    protected final void autoDelete(){
        if (model != null){
            ExpandableAdapter adapter = listener.getExpandableAdapter();
            if (adapter != null && adapter.deleteGroupModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }

    protected final void addChildModelable(@NonNull Modelable childModelable){
        if (model != null) {
            ExpandableAdapter adapter = listener.getExpandableAdapter();
            if (adapter != null && adapter.appendChildModelable(model, childModelable)){
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

    @Override
    public void onMakeToast(String line) {
        listener.onMakeToast(line);
    }

    @Override
    public void onMakeLog(String line) {
        listener.onMakeLog(line);
    }

    @Override
    public M getModelFromModelable(@NonNull Modelable modelable) {
        return null;
    }

    @Override
    public void onBindItemView() {

    }

    @Override
    public void onBindModel() {

    }
}
