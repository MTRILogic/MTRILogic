package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableAdapterListener;

@SuppressWarnings("unused")
public final class InflatableAdapter extends BaseAdapter {
    private final InflatableAdapterListener listener;
    private final LayoutInflater inflater;
    private final int typeCount;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public InflatableAdapter(@NonNull LayoutInflater inflater, int typeCount, @NonNull InflatableAdapterListener listener){
        this.typeCount = Math.max(typeCount, 1);
        this.listener = listener;
        this.inflater = inflater;
    }
    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public int getCount() {
        return getModelListable().getCount();
    }

    @Override
    public Model getItem(int position) {
        return getModel(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Model model = getItem(position);
        Inflatable<? extends Model> inflatable;
        if (convertView != null){
            inflatable = (Inflatable<? extends Model>) convertView.getTag();
        }else {
            inflatable = listener.getInflatable(model.getViewType(), inflater, parent);
            convertView = inflatable.getItemView();
            convertView.setTag(inflatable);
        }
        inflatable.bindModelable(model, position);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private Listable<Model> getModelListable(){
        return listener.getModelListable();
    }

    private Model getModel(int position){
        return getModelListable().get(position);
    }
}
