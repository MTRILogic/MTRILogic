package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class RecyclableAdapter extends RecyclerView.Adapter<Recyclable>{
    private static final String TAG = "RecyclableAdapter";
    private RecyclableListener listener;
    private ArrayList<Modelable> modelableList;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public RecyclableAdapter(RecyclableListener listener){
        this(listener, new ArrayList<Modelable>());
    }

    public RecyclableAdapter(RecyclableListener listener, ArrayList<Modelable> modelableList){
        this.listener = listener;
        this.modelableList = modelableList;
        setHasStableIds(true);
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public int getModelablePosition(Modelable modelable){
        return modelableList.indexOf(modelable);
    }

    public Modelable[] getModelableArray(){
        return modelableList.toArray(new Modelable[getItemCount()]);
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
        return isValidPosition(position) ? getItem(position) : null;
    }

    public Modelable setModelable(int position, Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position,modelable) : null;
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

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @NonNull
    @Override
    public Recyclable onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return listener.getRecyclable(viewType, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable holder, int position){
        Modelable modelable = getItem(position);
        holder.bindHolder(modelable, position);
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

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getItemCount();
    }

    private Modelable getItem(int position){
        return modelableList.get(position);
    }
}
