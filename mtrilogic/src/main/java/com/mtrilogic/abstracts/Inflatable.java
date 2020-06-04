package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.InflatableItemListener;

@SuppressWarnings({"unused"})
public abstract class Inflatable<M extends Modelable, L extends InflatableItemListener> implements Bindable<M> {
    protected final View itemView;
    protected final L listener;
    protected int position;
    protected M model;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Inflatable(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public Inflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int position){
        this.model = getModelFromModelable(modelable);
        this.position = position;
        if (this.model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final M getModel(){
        return model;
    }

    protected final void autoDelete(){
        if (model != null){
            InflatableAdapter adapter = listener.getInflatableAdapter();
            if (adapter != null && adapter.getListable().getList().remove(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

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
