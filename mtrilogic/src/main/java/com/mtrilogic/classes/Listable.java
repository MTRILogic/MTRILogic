package com.mtrilogic.classes;

import android.os.Bundle;

import com.mtrilogic.abstracts.Modelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Listable<M extends Modelable>{
    private static final String LIST = "list", IDX = "idx";

    private ArrayList<M> list;
    private M lastItem;
    private long idx;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Listable(){
        this(new ArrayList<M>(), 0);
    }

    public Listable(@NonNull ArrayList<M> list, long idx){
        this.list = list;
        this.idx = idx;
    }

    public Listable(@NonNull Bundle data){
        list = data.getParcelableArrayList(LIST);
        if (list != null) {
            idx = data.getLong(IDX);
        }else {
            list = new ArrayList<>();
        }
    }

    public Listable(@NonNull Bundle data, @NonNull Mapable<M> mapable){
        this(data);
        for (M item : this.list){
            long itemId = item.getItemId();
            ArrayList<M> list = data.getParcelableArrayList(LIST + itemId);
            if (list != null) {
                long idx = data.getLong(IDX + itemId);
                Listable<M> listable = new Listable<>(list, idx);
                mapable.putListable(item, listable);
            }
        }
    }

    // ================< PUBLIC METHODS >===========================================================

    /**
     * Devuelve el index actual de ids de la lista.
     * Útil para crear nuevas listas a ser añadidas o insertadas.
     * @return El index actual de ids.
     */
    public final long getIdx(){
        return idx;
    }

    /**
     * Establece el actual index de ids de la lista de este listable.
     * Posiblemente removido en un futuro.
     * @param idx El nuevo index de ids.
     */
    public final void setIdx(long idx){
        this.idx = idx;
    }

    // APPEND >=====================================================================================

    /**
     * Añade un elemento al final de la lista.
     * @param item El elemento a ser añadido.
     * @return True si el elemento fue añadido exitosamente.
     */
    public final boolean append(@NonNull M item){
        if (list.add(item)){
            item.setItemId(idx++);
            return true;
        }
        return false;
    }

    /**
     * Añade una lista de elementos al final de la lista.
     * El idx debe ser generado a partir de idx actual al crear la lista añadida.
     * @param list La lista de elementos a ser añadida.
     * @param idx El nuevo index de ids.
     * @return True si la lista fue añadida.
     */
    public final boolean appendList(@NonNull ArrayList<M> list, long idx){
        if (this.list.addAll(list)){
            this.idx = idx;
            return true;
        }
        return false;
    }

    // INSERT >=====================================================================================

    /**
     * Inserta un elemento en la posición dada en la lista.
     * @param position La posición dada donde será insertado el elemento.
     * @param item El elemento a ser insertado.
     * @return True sí el elemento fue insertado en la lista.
     */
    public final boolean insert(int position, @NonNull M item){
        if(isValidPosition(position)){
            list.add(position, item);
            item.setItemId(idx++);
            return true;
        }
        return false;
    }

    /**
     * Inserta una lista de elementos a partir de una posición dada.
     * El idx debe ser generado a partir de idx actual al crear la lista insertada.
     * @param position la posición donde se insertará el elemento.
     * @param list La lista de elementos a insertar.
     * @param idx el indice actual de ids
     * @return True si la lista fue insertada correctamente.
     */
    public final boolean insertList(int position, @NonNull ArrayList<M> list, long idx){
        if (isValidPosition(position) && this.list.addAll(position, list)){
            this.idx = idx;
            return true;
        }
        return false;
    }

    // GET >========================================================================================

    /**
     * Devuelve la lista de elementos de este listable.
     * @return La lista de elementos.
     */
    public final ArrayList<M> getList(){
        return list;
    }

    /**
     * Devuelve un elemento en la lista en una posición dada.
     * @param position La posición del elemento en la lista.
     * @return El elemento retornado.
     */
    public final M get(int position){
        return isValidPosition(position) ? list.get(position) : null;
    }

    /**
     * Devuelve el último elemento resultante trás una operación de reemplazo o eliminación.
     * @return El último elemento.
     */
    public final M getLastItem(){
        return lastItem;
    }

    // SET >========================================================================================

    /**
     * Reemplaza la lista de elementos de este listable, así como su index de ids relacionado.
     * El idx debe ser generado al momento de crear y cargar con items la nueva lista.
     * @param list La nueva lista.
     * @param idx El index de ids de la nueva lista.
     * @return True si la nueva lista fue reemplazada.
     */
    public final boolean setList(@NonNull ArrayList<M> list, long idx){
        if (this.list != list) {
            this.list = list;
            this.idx = idx;
            return true;
        }
        return false;
    }

    /**
     * Reemplaza un elemento de la lista de este listable, conservando el mismo id.
     * @param position La posición del elemento a ser reemplazado.
     * @param item El elemento que reemplazará al actual.
     * @return True si el elemento fue reemplazado.
     */
    public final boolean set(int position, @NonNull M item){
        lastItem = null;
        if (isValidPosition(position)){
            lastItem = list.set(position, item);
            item.setItemId(lastItem.getItemId());
            return true;
        }
        return false;
    }

    // CONTAINS >===================================================================================

    /**
     * Determina si el elemento dado está contenido en la lista.
     * @param item El elemento dado.
     * @return True sí el elemento está contenido en la lista.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public final boolean contains(@NonNull M item){
        return list.contains(item);
    }

    // DELETE >=====================================================================================

    /**
     * Borra el elemento dado de la lista.
     * @param item El elemento a ser borrado de la lista.
     * @return True sí el elemento fue borrado de la lista.
     */
    public final boolean delete(@NonNull M item){
        return list.remove(item);
    }

    /**
     * Borra el elemento de la posición dada de la lista, salvando el elemento en una variable.
     * El elemento puede ser recuperado con el método getLastItem.
     * @param position La posición del elemento que será borrado.
     * @return True sí el elemento fue borrado de la lista.
     */
    public final boolean delete(int position){
        lastItem = null;
        if (isValidPosition(position)){
            lastItem = list.remove(position);
            return true;
        }
        return false;
    }

    public final boolean deleteList(@NonNull ArrayList<M> list){
        return this.list.removeAll(list);
    }

    // COUNT >======================================================================================

    /**
     * Devuelve el número de elementos de la lista.
     * @return El número de elementos de la lista.
     */
    public final int getCount(){
        return list.size();
    }

    // RESET >======================================================================================

    /**
     * Limpia los elementos de la lista y pone el index de ids en cero.
     */
    public final void reset(){
        list.clear();
        idx = 0;
    }

    // SAVE TO DATA >===============================================================================

    public final void saveToData(@NonNull Bundle data){
        data.putParcelableArrayList(LIST, list);
        data.putLong(IDX, idx);
    }

    public final void saveToData(@NonNull Bundle data, @NonNull Mapable<M> mapable){
        saveToData(data);
        for (M item : this.list){
            long itemId = item.getItemId();
            Listable<M> listable = mapable.getListable(item);
            ArrayList<M> list = listable.getList();
            long idx = listable.getIdx();
            data.putParcelableArrayList(LIST + itemId, list);
            data.putLong(LIST + itemId, idx);
        }
    }

    // ================< PRIVATE METHODS >==========================================================

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < list.size();
    }
}
