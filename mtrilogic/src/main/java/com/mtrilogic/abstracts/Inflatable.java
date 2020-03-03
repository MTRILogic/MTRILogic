package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;

@SuppressWarnings({"unused"})
public abstract class Inflatable<M extends Modelable> extends LiveData<M>
        implements Observer<M> {

    protected final InflatableAdapterListener listener;
    protected final View itemView;
    protected int position;
    protected M model;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract void onBindHolder(@NonNull Modelable modelable);

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Inflatable(@NonNull View itemView, @NonNull InflatableAdapterListener listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public Inflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull InflatableAdapterListener listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int position){
        this.position = position;
        onBindHolder(modelable);
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            if (adapter.removeModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
