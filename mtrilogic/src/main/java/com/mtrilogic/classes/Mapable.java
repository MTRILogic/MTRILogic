package com.mtrilogic.classes;

import com.mtrilogic.abstracts.Modelable;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Mapable<M extends Modelable>{
    private Map<M, Listable<M>> listableMap;
    private Listable<M> lastListable;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Mapable(){
        this(new LinkedHashMap<M, Listable<M>>());
    }

    public Mapable(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        this.listableMap = listableMap;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final boolean putListable(@NonNull M itemKey, @NonNull Listable<M> listable){
        lastListable = listableMap.put(itemKey, listable);
        return lastListable == null;
    }

    /**
     * Retorna el actual mapa de listables.
     * @return El mapa de listables.
     */
    public final Map<M, Listable<M>> getListableMap(){
        return listableMap;
    }

    /**
     * Retorna el listable relacionado con la clave itemKey.
     * @param itemKey La clave del listable a retornar.
     * @return El listable relacionado.
     */
    public final Listable<M> getListable(@NonNull M itemKey){
        return listableMap.get(itemKey);
    }

    /**
     * Retorna el último listable retornado por una operación de delete o put.
     * @return El último listable retornado.
     */
    public final Listable<M> getLastListable() {
        return lastListable;
    }

    /**
     * Establece un nuevo mapa de listables.
     * @param listableMap El nuevo mapa de listables.
     * @return true si fue reemplazado el antiguo mapa por el nuevo.
     */
    public final boolean setListableMap(@NonNull LinkedHashMap<M, Listable<M>> listableMap){
        if (!listableMap.equals(this.listableMap)) {
            this.listableMap = listableMap;
            return true;
        }
        return false;
    }

    /**
     * Deletes the modelable's list from modelable's map
     * @param itemKey The modelable key
     * @return The removed modelable's list
     */
    public final boolean deleteListable(@NonNull M itemKey){
        lastListable = listableMap.remove(itemKey);
        return lastListable != null;
    }
}
