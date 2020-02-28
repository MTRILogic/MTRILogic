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

    private static final String TAG = "InflatableAdapter";
    private LayoutInflater inflater;
    private InflatableListener listener;
    private ArrayList<Modelable> modelableList;
    private int typeCount;
    private boolean stableIds;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableAdapter(Context context, @NonNull InflatableListener listener,
                             @NonNull ArrayList<Modelable> modelableList, int typeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.modelableList = modelableList;
        setTypeCount(typeCount);
        stableIds = true;
    }

    // ================< PUBLIC METHODS >===========================================================

    public void setTypeCount(int typeCount){
        typeCount = typeCount > 0 ? typeCount : 1;
        this.typeCount = typeCount;
    }

    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    public int getModelablePosition(@NonNull Modelable modelable){
        return modelableList.indexOf(modelable);
    }

    @NonNull
    private Modelable[] getModelableArray(){
        return modelableList.toArray(new Modelable[getCount()]);
    }

    @NonNull
    public ArrayList<Modelable> getModelableList(){
        return modelableList;
    }

    public void setModelableList(@NonNull ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public boolean addModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.addAll(modelableList);
    }

    public boolean insertModelableList(int position, @NonNull ArrayList<Modelable> modelableList){
        return isValidPosition(position) && this.modelableList.addAll(position, modelableList);
    }

    public boolean removeModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.removeAll(modelableList);
    }

    public boolean retainModelableList(@NonNull ArrayList<Modelable> modelableList){
        return this.modelableList.retainAll(modelableList);
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    public Modelable setModelable(int position, @NonNull Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    public boolean addModelable(@NonNull Modelable modelable){
        return modelableList.add(modelable);
    }

    public void insertModelable(int position, @NonNull Modelable modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
        }
    }

    public boolean removeModelable(@NonNull Modelable modelable){
        return modelableList.remove(modelable);
    }

    public void clearModelableList(){
        modelableList.clear();
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getCount(){
        return modelableList.size();
    }

    @Override
    public Modelable getItem(int position){
        return modelableList.get(position);
        // you should not use this method to get a modelable,
        // instead you should use getModelable(int) method
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
        inflatable.onBindHolder(modelable, position);
        return convertView;
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
