package com.mtrilogic.classes;

import android.os.Bundle;

import com.mtrilogic.abstracts.Modelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Listable<M extends Modelable>{
    private static final String LIST = "list", IDX = "idx";
    private ArrayList<M> modelableList;
    private long idx;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Listable(){
        this(new ArrayList<M>(), 0);
    }

    public Listable(@NonNull ArrayList<M> modelableList, long idx){
        this.modelableList = modelableList;
        this.idx = idx;
    }

    // ================< PUBLIC METHODS >===========================================================

    // IDX
    public final long getIdx(){
        return idx;
    }

    public final void setIdx(long idx){
        this.idx = idx;
    }

    // APPEND
    public final boolean appendModelable(@NonNull M modelable){
        return modelableList.add(modelable);
    }

    // INSERT
    public final boolean insertModelable(int position, @NonNull M modelable){
        if(isValidPosition(position)){
            modelableList.add(position, modelable);
            return true;
        }
        return false;
    }

    // GET
    public final ArrayList<M> getModelableList(){
        return modelableList;
    }

    public final Modelable getModelable(int position){
        return isValidPosition(position) ? modelableList.get(position) : null;
    }

    // SET
    public final void setModelableList(@NonNull ArrayList<M> modelableList){
        this.modelableList = modelableList;
    }

    public final Modelable setModelable(int position, @NonNull M modelable){
        return isValidPosition(position) ? modelableList.set(position, modelable) : null;
    }

    // CONTAINS
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public final boolean containsModelable(@NonNull M modelable){
        return modelableList.contains(modelable);
    }

    // DELETE
    public final boolean deleteModelable(@NonNull M modelable){
        return modelableList.remove(modelable);
    }

    // COUNT
    public final int getModelableCount(){
        return modelableList.size();
    }

    // RESET
    public final void reset(){
        modelableList.clear();
        idx = 0;
    }

    public final void restoreFromData(@NonNull Bundle data){
        modelableList = data.getParcelableArrayList(LIST);
        idx = data.getLong(IDX);
    }

    public final void restoreFromData(@NonNull Bundle data, @NonNull Mapable<M> mapable){
        restoreFromData(data);
        if (this.modelableList != null){
            for (M modelable : this.modelableList){
                long itemId = modelable.getItemId();
                ArrayList<M> modelableList = data.getParcelableArrayList(LIST + itemId);
                long idx = data.getLong(IDX + itemId);
                if (modelableList != null) {
                    Listable<M> listable = new Listable<>(modelableList, idx);
                    mapable.putListable(modelable, listable);
                }
            }
        }else {
            modelableList = new ArrayList<>();
            idx = 0;
        }
    }

    public final void saveToData(@NonNull Bundle data){
        data.putParcelableArrayList(LIST, modelableList);
        data.putLong(IDX, idx);
    }

    public final void saveToData(@NonNull Bundle data, @NonNull Mapable<M> mapable){
        saveToData(data);
        for (M modelable : this.modelableList){
            long itemId = modelable.getItemId();
            Listable<M> listable = mapable.getListable(modelable);
            ArrayList<M> modelableList = listable.getModelableList();
            long idx = listable.getIdx();
            data.putParcelableArrayList(LIST + itemId, modelableList);
            data.putLong(LIST + itemId, idx);
        }
    }

    // ================< PRIVATE METHODS >==========================================================

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getModelableCount();
    }
}
