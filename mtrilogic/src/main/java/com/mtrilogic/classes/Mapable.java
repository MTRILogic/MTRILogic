package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Mapable<M extends Modelable>{
    private Map<M, Listable<M>> listableMap;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Mapable(){
        this(new LinkedHashMap<M, Listable<M>>());
    }

    public Mapable(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        this.listableMap = listableMap;
    }

    // ================< PUBLIC METHODS >===========================================================

    // PUT
    public final Listable<M> putListable(@NonNull M modelable, @NonNull Listable<M> listable){
        return listableMap.put(modelable, listable);
    }

    // GET
    public final Map<M, Listable<M>> getListableMap(){
        return listableMap;
    }

    public final Listable<M> getListable(@NonNull M modelable){
        return listableMap.get(modelable);
    }

    // SET
    public final void setListableMap(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        this.listableMap = listableMap;
    }

    /**
     * Checks if this map contains a mapping for the especified modelable.
     * @param modelable the modelable key to be tested.
     * @return true if this map contains a mapping for modelable key
     */
    public final boolean containsModelableKey(@NonNull M modelable){
        return listableMap.containsKey(modelable);
    }

    /**
     * Deletes the modelable's list from modelable's map
     * @param modelable The modelable key
     * @return The removed modelable's list
     */
    public final Listable<M> deleteListable(@NonNull M modelable){
        return listableMap.remove(modelable);
    }

    /**
     * Get listable's map size.
     * @return The size listable's map.
     */
    public final int getListableCount(){
        return listableMap.size();
    }

    /**
     * Clear the listable map.
     */
    public final void reset(){
        listableMap.clear();
    }
}
