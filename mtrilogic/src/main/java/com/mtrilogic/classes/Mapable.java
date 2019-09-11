package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Mapable{
    private Map<Modelable, Listable> listableMap;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public Mapable(){
        listableMap = new LinkedHashMap<>();
    }

    public Mapable(@NonNull LinkedHashMap<Modelable, Listable> listableMap){
        this.listableMap = listableMap;
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

    // PUT ====================================================================

    public Listable putListable(Modelable modelable, Listable listable){
        return listableMap.put(modelable, listable);
    }

    // GET ====================================================================

    public Map<Modelable, Listable> getListableMap(){
        return listableMap;
    }

    public Listable getListable(Modelable modelable){
        return listableMap.get(modelable);
    }

    // SET ====================================================================

    public void setListableMap(@NonNull LinkedHashMap<Modelable, Listable> listableMap){
        this.listableMap = listableMap;
    }

    // CONTAINS ===============================================================

    public boolean containsModelableKey(Modelable modelable){
        return listableMap.containsKey(modelable);
    }

    // DELETE =================================================================

    public Listable deleteListable(Modelable modelable){
        return listableMap.remove(modelable);
    }

    // COUNT ==================================================================

    public int getListableCount(){
        return listableMap.size();
    }

    // RESET ==================================================================

    public void reset(){
        listableMap.clear();
    }
}
