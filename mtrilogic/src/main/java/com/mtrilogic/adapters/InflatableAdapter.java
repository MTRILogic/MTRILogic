package com.mtrilogic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.InflatableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public class InflatableAdapter extends BaseAdapter{
    private static final String TAG = "InflatableAdapter";
    private InflatableListener listener;
    private ArrayList<Modelable> modelableList;
    private int typeCount;
    private boolean stableIds;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public InflatableAdapter(InflatableListener listener, ArrayList<Modelable> modelableList,
                             int typeCount){
        this.listener = listener;
        this.modelableList = modelableList;
        setTypeCount(typeCount);
        stableIds = true;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void setTypeCount(int typeCount){
        typeCount = typeCount > 0 ? typeCount : 1;
        this.typeCount = typeCount;
    }

    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    public int getModelablePosition(Modelable modelable){
        return modelableList.indexOf(modelable);
    }

    private Modelable[] getModelableArray(){
        return modelableList.toArray(new Modelable[getCount()]);
    }

    public ArrayList<Modelable> getModelableList(){
        return modelableList;
    }

    public void setModelableList(ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public boolean addModelableList(ArrayList<Modelable> modelableList){
        return this.modelableList.addAll(modelableList);
    }

    public boolean insertModelableList(int position, ArrayList<Modelable> modelableList){
        return isValidPosition(position) && this.modelableList.addAll(position, modelableList);
    }

    public boolean removeModelableList(ArrayList<Modelable> modelableList){
        return this.modelableList.removeAll(modelableList);
    }

    public boolean retainModelableList(ArrayList<Modelable> modelableList){
        return this.modelableList.retainAll(modelableList);
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    public Modelable setModelable(int position, Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    public boolean addModelable(Modelable modelable){
        return modelableList.add(modelable);
    }

    public void insertModelable(int position, Modelable modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
        }
    }

    public boolean removeModelable(Modelable modelable){
        return modelableList.remove(modelable);
    }

    public void clearModelableList(){
        modelableList.clear();
    }

// +++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++

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
            inflatable = listener.getInflatable(viewType, parent);
            convertView = inflatable.getItemView();
            convertView.setTag(inflatable);
        }
        inflatable.onBindHolder(modelable, position);
        return convertView;
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

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getCount();
    }
}
