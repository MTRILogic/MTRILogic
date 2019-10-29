package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Listable<M extends Modelable>{
    private ArrayList<M> modelableList;
    private long idx;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Listable(){
        modelableList = new ArrayList<>();
    }

    public Listable(@NonNull ArrayList<M> modelableList, long idx){
        this.modelableList = modelableList;
        this.idx = idx;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // IDX
    public long getIdx(){
        return idx;
    }

    public void setIdx(long idx){
        this.idx = idx;
    }

    // APPEND
    public boolean appendModelable(@NonNull M modelable){
        return modelableList.add(modelable);
    }

    // INSERT
    public boolean insertModelable(int position, @NonNull M modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
            return true;
        }
        return false;
    }

    // GET
    public ArrayList<M> getModelableList(){
        return modelableList;
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    // SET
    public void setModelableList(@NonNull ArrayList<M> modelableList){
        this.modelableList = modelableList;
    }

    public Modelable setModelable(int position, @NonNull M modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    // CONTAINS
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean containsModelable(@NonNull M modelable){
        return modelableList.contains(modelable);
    }

    // DELETE
    public boolean deleteModelable(@NonNull M modelable){
        return modelableList.remove(modelable);
    }

    // COUNT
    public int getModelableCount(){
        return modelableList.size();
    }

    // RESET
    public void reset(){
        modelableList.clear();
        idx = 0;
    }

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getModelableCount();
    }
}
