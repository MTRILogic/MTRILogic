package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExpandableChild <M extends Modelable, L extends ExpandableItemListener>
        extends LiveData<M> implements Bindable<M>, Observer<M> {
    protected final View itemView;
    protected final L listener;

    protected int groupPosition;
    protected int childPosition;
    protected boolean lastChild;
    protected M model;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableChild(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public ExpandableChild(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                           @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int groupPosition, int childPosition,
                                boolean lastChild){
        model = getModelFromModelable(modelable);
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        if (model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        if (model != null){
            ExpandableAdapter adapter = listener.getExpandableAdapter();
            if (adapter != null){
                Modelable groupModelable = adapter.getGroupModelable(groupPosition);
                if (groupModelable != null){
                    if (adapter.deleteChildModelable(groupModelable, model)){
                        adapter.notifyDataSetChanged();
                        if (adapter.getChildrenCount(groupPosition) == 0){
                            ExpandableView lvwItems = listener.getExpandableView();
                            if (lvwItems != null && lvwItems.isGroupExpanded(groupPosition)){
                                lvwItems.collapseGroup(groupPosition);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onChanged(M model) {

    }

    @Override
    public void onBindItemView() {

    }

    @Override
    public void onMakeToast(String line) {
        listener.onMakeToast(line);
    }

    @Override
    public void onMakeLog(String line) {
        listener.onMakeLog(line);
    }
}
