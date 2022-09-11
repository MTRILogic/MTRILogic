package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.interfaces.ModelBindable;

@SuppressWarnings("unused")
public abstract class Inflatable<M extends Model> implements ModelBindable {
    protected final InflatableItemListener listener;
    protected final View itemView;

    private final Class<M> clazz;

    protected int position;
    protected M model;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public Inflatable(@NonNull Class<M> clazz, @NonNull View itemView, @NonNull InflatableItemListener listener){
        this.itemView = itemView;
        this.listener = listener;
        this.clazz = clazz;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public View getItemView() {
        itemView.setOnClickListener(v -> listener.onItemClick(itemView, model, position));
        itemView.setOnLongClickListener(v -> listener.onItemLongClick(itemView, model, position));
        onBindItemView();
        return itemView;
    }

    public void bindModelable(@NonNull Model model, int position){
        this.model = clazz.cast(model);
        this.position = position;
        onBindModel();
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected final boolean autoDelete(){
        return listener.getModelListable().delete(model);
    }

    protected final void notifyChanged(){
        listener.getInflatableAdapter().notifyDataSetChanged();
    }

    protected final void makeToast(String line){
        listener.onMakeToast(line);
    }
}
