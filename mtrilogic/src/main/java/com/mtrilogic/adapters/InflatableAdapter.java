package com.mtrilogic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableListener;

import java.util.ArrayList;
import java.util.List;

public class InflatableAdapter extends BaseAdapter{
    @SuppressWarnings("unused")
    private static final String TAG = "InflatableAdapterTAG";
    private InflatableListener listener;
    private List<Modelable> models;
    private int typeCount;

    @SuppressWarnings("unused")
    public InflatableAdapter(InflatableListener listener){
        this(listener,1);
    }

    @SuppressWarnings("WeakerAccess")
    public InflatableAdapter(InflatableListener listener, int typeCount){
        this(listener,new ArrayList<Modelable>(),typeCount);
    }

    @SuppressWarnings("unused")
    public InflatableAdapter(InflatableListener listener, List<Modelable> models){
        this(listener,models,1);
    }

    @SuppressWarnings("WeakerAccess")
    public InflatableAdapter(InflatableListener listener, List<Modelable> models, int typeCount){
        this.listener = listener;
        this.models = models;
        typeCount = typeCount > 0 ? typeCount : 1;
        this.typeCount = typeCount;
    }

    public void setModels(List<Modelable> models){
        this.models = models;
    }

    public List<Modelable> getModels(){
        return models;
    }

    @SuppressWarnings("unused")
    public boolean addModel(Modelable model){
        if(!models.contains(model)){
            return models.add(model);
        }
        return false;
    }

    @SuppressWarnings("unused")
    public boolean removeModel(int position){
        if(position > -1 && position < models.size()){
            return models.remove(models.get(position));
        }
        return false;
    }

    @SuppressWarnings("unused")
    public void clearModels(){
        models.clear();
    }

    @Override
    public int getCount(){
        return models.size();
    }

    @Override
    public Modelable getItem(int position){
        return models.get(position);
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Modelable model = getItem(position);
        model.setPosition(position);
        Inflatable item;
        if(convertView != null){
            item = (Inflatable)convertView.getTag();
        }else{
            int viewType = model.getViewType();
            item = listener.getInflatableItem(viewType);
            convertView = item.getInflatableView(parent);
        }
        item.onBindHolder(model);
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
}
