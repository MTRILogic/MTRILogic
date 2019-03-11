package com.mtrilogic.adapters;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","WeakerAccess"})
public class InflatableAdapter extends BaseAdapter{
    private static final String TAG = "InflatableAdapterTAGY";
    private static final String MODELABLES = "modelables";
    private static final int NO_ITEMS = -1;
    private InflatableListener listener;
    private ArrayList<Modelable> modelableList;
    private int typeCount;
    private boolean stableIds;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public InflatableAdapter(InflatableListener listener){
        this(listener,1);
    }

    public InflatableAdapter(InflatableListener listener, int typeCount){
        this(listener,new ArrayList<Modelable>(),typeCount);
    }

    public InflatableAdapter(InflatableListener listener, ArrayList<Modelable> modelableList){
        this(listener,modelableList,1);
    }

    public InflatableAdapter(InflatableListener listener, ArrayList<Modelable> modelableList, int typeCount){
        this.listener = listener;
        this.modelableList = modelableList;
        typeCount = typeCount > 0 ? typeCount : 1;
        this.typeCount = typeCount;
        stableIds = true;
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    private Modelable[] getModelables(){
        return modelableList.toArray(new Modelable[getCount()]);
    }

    public void setModelableList(ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public List<Modelable> getModelableList(){
        return modelableList;
    }

    public boolean addModelable(Modelable modelable){
        return !modelableList.contains(modelable) && modelableList.add(modelable);
        // the modelable must have at least different id
        // otherwise, you should use an external list
    }

    public Modelable setModelable(int position, Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    public boolean removeModelable(int position){
        return isValidPosition(position) && modelableList.remove(modelableList.get(position));
    }

    public void clearModelableList(){
        modelableList.clear();
    }

    public void onRestoreModelableInstance(Bundle instance){
        if(instance != null){
            modelableList = instance.getParcelableArrayList(MODELABLES);
        }
    }

    public void onSaveModelableInstance(Bundle instance){
        if(getCount() > 0){
            instance.putParcelableArrayList(MODELABLES,modelableList);
        }
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

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
        modelable.setPosition(position);
        Inflatable item;
        if(convertView != null){
            item = (Inflatable)convertView.getTag();
        }else{
            int viewType = modelable.getViewType();
            item = listener.getInflatableItem(viewType);
            convertView = item.getInflatableView(parent);
        }
        item.onBindHolder(modelable);
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

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > NO_ITEMS && position < getCount();
    }
}
