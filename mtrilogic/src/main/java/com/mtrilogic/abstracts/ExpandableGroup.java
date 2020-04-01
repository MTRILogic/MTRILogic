package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.views.ExpandableView;

import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExpandableGroup<M extends Modelable, L extends ExpandableItemListener>
        extends LiveData<M> implements Bindable<M>, Observer<M> {
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

    public ExpandableGroup(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                           @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
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

    protected final void autoDelete(){
        if (model != null){
            ExpandableAdapter adapter = listener.getExpandableAdapter();
            if (adapter != null){
                Listable<Modelable> listable = adapter.getGroupListable();
                if (listable != null){
                    ArrayList<Modelable> list = listable.getList();
                    if (list != null){
                        if (list.remove(model)){
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    protected final void addChildModelable(@NonNull Modelable childModelable){
        if (model != null){
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
