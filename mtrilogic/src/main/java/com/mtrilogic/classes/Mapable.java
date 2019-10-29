package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Mapable<M extends Modelable>{
    private Map<M, Listable<M>> listableMap;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Mapable(){
        listableMap = new LinkedHashMap<>();
    }

    public Mapable(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        this.listableMap = listableMap;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // PUT
    public Listable<M> putListable(M modelable, Listable<M> listable){
        return listableMap.put(modelable, listable);
    }

    // GET
    public Map<M, Listable<M>> getListableMap(){
        return listableMap;
    }

    public Listable<M> getListable(M modelable){
        return listableMap.get(modelable);
    }

    // SET
    public void setListableMap(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        this.listableMap = listableMap;
    }

    // CONTAINS
    public boolean containsModelableKey(M modelable){
        return listableMap.containsKey(modelable);
    }

    // DELETE
    public Listable<M> deleteListable(M modelable){
        return listableMap.remove(modelable);
    }

    // COUNT
    public int getListableCount(){
        return listableMap.size();
    }

    // RESET
    public void reset(){
        listableMap.clear();
    }
}
