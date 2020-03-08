package com.mtrilogic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.InflatableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public class InflatableAdapter extends BaseAdapter{
    private ArrayList<Modelable> modelableList;
    private InflatableListener listener;
    private LayoutInflater inflater;
    private boolean stableIds;
    private int typeCount;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableAdapter(@NonNull Context context, @NonNull InflatableListener listener,
                             @NonNull ArrayList<Modelable> modelableList, int typeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.modelableList = modelableList;
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

    public final int getModelablePosition(@NonNull Modelable modelable){
        return modelableList.indexOf(modelable);
    }

    public final Modelable[] getModelableArray(){
        return modelableList.toArray(new Modelable[getCount()]);
    }

    public final ArrayList<Modelable> getModelableList(){
        return modelableList;
    }

    public final void setModelableList(@NonNull ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public final boolean addModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.addAll(modelableList);
    }

    public final boolean insertModelableList(int position, @NonNull ArrayList<Modelable> modelableList){
        return isValidPosition(position) && this.modelableList.addAll(position, modelableList);
    }

    public final boolean removeModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.removeAll(modelableList);
    }

    public final boolean retainModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.retainAll(modelableList);
    }

    public final Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    public final Modelable setModelable(int position, @NonNull Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    public final boolean addModelable(@NonNull Modelable modelable){
        return modelableList.add(modelable);
    }

    public final void insertModelable(int position, @NonNull Modelable modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
        }
    }

    public final boolean removeModelable(@NonNull Modelable modelable){
        return modelableList.remove(modelable);
    }

    public final void clearModelableList(){
        modelableList.clear();
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getCount(){
        return modelableList.size();
    }

    /**
     * you should not use this method to get a modelable,
     * instead you should use getModelable(int) method
     * @param position the modelable position.
     * @return the modelable at position.
     */
    @Override
    public Modelable getItem(int position){
        return modelableList.get(position);
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Modelable modelable = getItem(position);
        Inflatable inflatable;
        if(convertView != null){
            inflatable = (Inflatable)convertView.getTag();
        }else{
            int viewType = modelable.getViewType();
            inflatable = listener.getInflatable(viewType, inflater, parent);
            convertView = inflatable.getItemView();
            convertView.setTag(inflatable);
        }
        inflatable.bindHolder(modelable, position);
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
        return getItem(position).isEnabled();
    }

    @Override
    public int getItemViewType(int position){
        return getItem(position).getViewType();
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

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getCount();
    }
}
