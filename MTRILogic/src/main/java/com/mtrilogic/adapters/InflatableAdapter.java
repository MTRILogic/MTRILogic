package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class InflatableAdapter extends BaseAdapter {
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
        return getModelableList().size();
    }

    @Override
    public Modelable getItem(int position) {
        return getModelable(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Modelable modelable = getItem(position);
        Inflatable<? extends Modelable> inflatable;
        if (convertView != null){
            inflatable = (Inflatable<? extends Modelable>) convertView.getTag();
        }else {
            inflatable = listener.getInflatable(modelable.getViewType(), inflater, parent);
            convertView = inflatable.getItemView();
            convertView.setTag(inflatable);
        }
        inflatable.bindModelable(modelable, position);
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

    private ArrayList<Modelable> getModelableList(){
        return listener.getModelableListable().getList();
    }

    private Modelable getModelable(int position){
        return getModelableList().get(position);
    }
}
