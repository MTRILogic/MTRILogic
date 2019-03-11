package com.mtrilogic.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","WeakerAccess"})
public class RecyclableAdapter extends RecyclerView.Adapter<Recyclable>{
    private static final String TAG = "RecyclableAdapterTAGY";
    private static final String MODELABLES = "modelables";
    private static final int NO_ITEMS = -1;
    private RecyclableListener listener;
    private ArrayList<Modelable> modelableList;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public RecyclableAdapter(RecyclableListener listener){
        this(listener,new ArrayList<Modelable>());
    }

    public RecyclableAdapter(RecyclableListener listener, ArrayList<Modelable> modelableList){
        this.listener = listener;
        this.modelableList = modelableList;
        setHasStableIds(true);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public Modelable[] getModelables(){
        return modelableList.toArray(new Modelable[getItemCount()]);
    }

    public void setModelableList(ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public List<Modelable> getModelableList(){
        return modelableList;
    }

    public boolean addModelable(Modelable modelable){
        return !modelableList.contains(modelable) && modelableList.add(modelable);
        //the modelable must have at least different id
        //otherwise, you should use an external list
    }

    public Modelable setModelable(int position, Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position,modelable) : null;
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? getItem(position) : null;
    }

    public boolean removeModelable(int position){
        return isValidPosition(position) && modelableList.remove(getItem(position));
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
        if(getItemCount() > 0){
            instance.putParcelableArrayList(MODELABLES,modelableList);
        }
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @NonNull
    @Override
    public Recyclable onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return listener.getRecyclableItem(viewType).getRecyclableHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable holder, int position){
        Modelable modelable = getItem(position);
        modelable.setPosition(position);
        holder.onBindHolder(modelable);
    }

    @Override
    public int getItemCount(){
        return modelableList.size();
    }

    @Override
    public int getItemViewType(int position){
        return getItem(position).getViewType();
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getItemId();
    }

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > NO_ITEMS && position < getItemCount();
    }

    private Modelable getItem(int position){
        return modelableList.get(position);
    }
}
