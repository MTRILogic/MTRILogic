package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class Listable{
    private ArrayList<Modelable> modelableList;
    private boolean live;
    private long idx;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public Listable(){
        modelableList = new ArrayList<>();
        live = true;
    }

    public Listable(@NonNull ArrayList<Modelable> modelableList, long idx){
        this.modelableList = modelableList;
        this.idx = idx;
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

    // LIVE ===================================================================

    public boolean isLive(){
        return live;
    }

    public void setLive(boolean live){
        this.live = live;
    }

    // IDX ====================================================================

    public long getIdx(){
        return idx;
    }

    public void setIdx(long idx){
        this.idx = idx;
    }

    // ARRAY ==================================================================

    public Modelable[] getModelableArray(){
        return modelableList.toArray(new Modelable[getModelableCount()]);
    }

    // APPEND =================================================================

    public boolean appendModelable(@NonNull Modelable modelable){
        return modelableList.add(modelable);
    }

    // INSERT =================================================================

    public boolean insertModelable(int position, @NonNull Modelable modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
            return true;
        }
        return false;
    }

    // GET ====================================================================

    public ArrayList<Modelable> getModelableList(){
        return modelableList;
    }

    public Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    // SET ====================================================================

    public void setModelableList(@NonNull ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public Modelable setModelable(int position, @NonNull Modelable modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    // CONTAINS ===============================================================

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean containsModelable(@NonNull Modelable modelable){
        return modelableList.contains(modelable);
    }

    // DELETE =================================================================

    public boolean deleteModelable(@NonNull Modelable modelable){
        return modelableList.remove(modelable);
    }

    // COUNT ==================================================================

    public int getModelableCount(){
        return modelableList.size();
    }

    // RESET ==================================================================

    public void reset(){
        modelableList.clear();
        idx = 0;
    }

// ++++++++++++++++| PRIVATE METHODS |+++++++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getModelableCount();
    }
}
