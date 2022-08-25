package com.mtrilogic.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.Observable;
import com.mtrilogic.interfaces.Observer;
import com.mtrilogic.interfaces.OnIterationListener;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Listable<M extends Modelable> {
    private static final String LIST = "list", IDX = "idx";

    private final ArrayList<M> list;
    private long idx;

    private M lastItem;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Listable(){
        list = new ArrayList<>();
    }

    public Listable(@NonNull ArrayList<M> list, long idx){
        this.list = list;
        this.idx = idx;
    }

    public Listable(@NonNull Bundle data, long itemId){
        this(data, LIST + itemId, IDX + itemId);
    }

    public Listable(@NonNull Bundle data){
        this(data, LIST, IDX);
    }

    /*==============================================================================================
    PRIVATE CONSTRUCTOR
    ==============================================================================================*/

    private Listable(@NonNull Bundle data, @NonNull String keyList, @NonNull String keyIdx){
        ArrayList<M> list = data.getParcelableArrayList(keyList);
        if (list != null){
            idx = data.getLong(keyIdx);
        }else {
            list = new ArrayList<>();
        }
        this.list = list;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    // RESTORABLE METHODS ==========================================================================

    public void saveToData(@NonNull Bundle data, long itemId){
        saveToData(data, LIST + itemId, IDX + itemId);
    }

    public void saveToData(@NonNull Bundle data){
        saveToData(data, LIST, IDX);
    }

    private void saveToData(@NonNull Bundle data, @NonNull String keyList, @NonNull String keyIdx){
        data.putParcelableArrayList(keyList, list);
        data.putLong(keyIdx, idx);
    }

    // ITERATE METHOD ==============================================================================

    public void iterateList(@NonNull OnIterationListener<M> listener){
        for (M item : list){
            listener.onIteration(item);
        }
    }

    // ATTACH METHOD ===============================================================================

    public void attach(@NonNull M item, @NonNull Observable observable){
        if (item instanceof Observer){
            observable.attach((Observer) item);
        }
    }

    public void attachList(@NonNull Observable observable){
        iterateList(item -> attach(item, observable));
    }

    // DETACH METHOD ==============================================================================

    public void detach(@NonNull M item, @NonNull Observable observable){
        if (item instanceof Observer){
            observable.detach((Observer) item);
        }
    }

    public void detachList(@NonNull Observable observable){
        iterateList(item -> detach(item, observable));
    }

    // APPEND ======================================================================================

    public boolean append(@NonNull M item){
        return !list.contains(item) && list.add(item);
    }

    public boolean appendList(@NonNull ArrayList<M> list){
        return this.list.addAll(list);
    }

    // INSERT ======================================================================================

    public boolean insertList(int position, @NonNull ArrayList<M> list){
        return isValidPosition(position) && this.list.addAll(list);
    }

    public boolean insert(int position, @NonNull M item){
        if (isValidPosition(position) && !list.contains(item)){
            list.add(position, item);
            return true;
        }
        return false;
    }

    // GET =========================================================================================

    public ArrayList<M> getList() {
        return list;
    }

    public M get(int position){
        return isValidPosition(position) ? list.get(position) : null;
    }

    public M getLast() {
        return lastItem;
    }

    public long getIdx() {
        return idx;
    }

    public int getPosition(@NonNull M item){
        return list.indexOf(item);
    }

    public int getCount(){
        return list.size();
    }

    // SET =========================================================================================

    public boolean set(int position, @NonNull M item){
        if (isValidPosition(position) && !list.contains(item)){
            lastItem = list.set(position, item);
            return true;
        }
        lastItem = null;
        return false;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    // DELETE ======================================================================================

    public boolean deleteList(@NonNull ArrayList<M> list){
        return this.list.removeAll(list);
    }

    public boolean delete(int position){
        if (isValidPosition(position)){
            lastItem = list.remove(position);
            return true;
        }
        lastItem = null;
        return false;
    }

    public boolean delete(@NonNull M item){
        return list.remove(item);
    }

    // CONTAINS ====================================================================================

    public boolean containsList(@NonNull ArrayList<M> list){
        return this.list.containsAll(list);
    }

    public boolean contains(@NonNull M item){
        return list.contains(item);
    }

    // RETAIN ======================================================================================

    public boolean retainList(@NonNull ArrayList<M> list){
        return this.list.retainAll(list);
    }

    // CLEAR =======================================================================================

    public void clear(){
        list.clear();
        idx = 0;
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private boolean isValidPosition(int position){
        return position > -1 && position < getCount();
    }
}
