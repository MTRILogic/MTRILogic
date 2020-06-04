package com.mtrilogic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public class InflatableAdapter extends BaseAdapter{
    private Listable<Modelable> listable;
    private InflatableListener listener;
    private LayoutInflater inflater;
    private boolean stableIds;
    private int typeCount;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableAdapter(@NonNull Context context, @NonNull InflatableListener listener,
                             @NonNull Listable<Modelable> listable, int typeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.listable = listable;
        setTypeCount(typeCount);
        stableIds = true;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void setTypeCount(int typeCount){
        typeCount = typeCount > 0 ? typeCount : 1;
        this.typeCount = typeCount;
    }

    public final void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    public Listable<Modelable> getListable() {
        return listable;
    }

    public boolean setListable(@NonNull Listable<Modelable> listable) {
        if (this.listable != listable) {
            this.listable = listable;
            return true;
        }
        return false;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getCount(){
        return getModelableList().size();
    }

    /**
     * @param position the modelable position.
     * @return the modelable at position.
     */
    @Override
    public Modelable getItem(int position){
        return getModelable(position);
    }

    @Override
    public long getItemId(int position){
        return getModelable(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Modelable modelable = getModelable(position);
        Inflatable<?, ?> inflatable;
        if(convertView != null){
            inflatable = (Inflatable<?, ?>)convertView.getTag();
        }else{
            int viewType = modelable.getViewType();
            inflatable = listener.getInflatable(viewType, inflater, parent);
            convertView = inflatable.getItemView();
            convertView.setTag(inflatable);
        }
        inflatable.bindModel(modelable, position);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position){
        return getModelable(position).isEnabled();
    }

    @Override
    public int getItemViewType(int position){
        return getModelable(position).getViewType();
    }

    @Override
    public int getViewTypeCount(){
        return typeCount;
    }

    @Override
    public boolean hasStableIds(){
        return stableIds;
    }

    // ================< PRIVATE METHODS >==========================================================

    private Modelable getModelable(int position){
        return getModelableList().get(position);
    }

    private ArrayList<Modelable> getModelableList(){
        return listable.getList();
    }
}
