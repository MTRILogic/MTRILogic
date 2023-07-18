package com.mtrilogic.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.interfaces.Observable;
import com.mtrilogic.interfaces.OnIterationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class Mappable<M extends Model> {

    private final Map<M, Listable<M>> childListableMap = new HashMap<>();
    private final Listable<M> groupListable;

    private Listable<M> lastChildListable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Mappable(){
        groupListable = new Listable<>();
    }

    public Mappable(@NonNull Bundle data){
        groupListable = new Listable<>(data);
        iterateGroupList(group -> {
            Listable<M> listable = new Listable<>(data, group.getItemId());
            lastChildListable = childListableMap.put(group, listable);
        });
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void saveToData(@NonNull Bundle data){
        groupListable.saveToData(data);
        iterateGroupList(group -> {
            Listable<M> listable = getChildListable(group);
            if (listable != null) {
                listable.saveToData(data, group.getItemId());
            }
        });
    }

    // ITERATE METHODS >>***************************************************************************

    public void iterateGroupList(@NonNull OnIterationListener<M> listener){
        groupListable.iterateList(listener);
    }

    public void iterateChildList(@NonNull M group, @NonNull OnIterationListener<M> listener){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null){
            childListable.iterateList(listener);
        }
    }

    // ATTACH METHODS ==============================================================================

    public void attachGroupList(@NonNull Observable observable){
        groupListable.attachList(observable);
    }

    public void attachChildList(@NonNull M group, @NonNull Observable observable){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null){
            childListable.attachList(observable);
        }
    }

    public void attachGroup(@NonNull M group, @NonNull Observable observable){
        groupListable.attach(group, observable);
    }

    public void attachChild(@NonNull M group, @NonNull M child, @NonNull Observable observable){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null) {
            childListable.attach(child, observable);
        }
    }

    // DETACH METHODS ==============================================================================

    public void detachGroupList(@NonNull Observable observable){
        groupListable.detachList(observable);
    }

    public void detachChildList(@NonNull M group, @NonNull Observable observable){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null){
            childListable.detachList(observable);
        }
    }

    public void detachGroup(@NonNull M group, @NonNull Observable observable){
        groupListable.detach(group, observable);
    }

    public void detachChild(@NonNull M group, @NonNull M child, @NonNull Observable observable){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null) {
            childListable.detach(child, observable);
        }
    }

    // APPEND METHODS ==============================================================================

    public boolean appendGroup(@NonNull M group){
        return appendGroup(group, new Listable<>());
    }

    public boolean appendGroup(@NonNull M group, @NonNull Listable<M> childListable){
        if (groupListable.append(group)){
            lastChildListable = childListableMap.put(group, childListable);
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean appendGroupList(@NonNull ArrayList<M> groupList){
        if (groupListable.appendList(groupList)){
            for (M group : groupList){
                lastChildListable = childListableMap.put(group, new Listable<>());
            }
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean appendChild(@NonNull M group, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.append(child);
    }

    public boolean appendChildList(@NonNull M group, @NonNull ArrayList<M> childList){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.appendList(childList);
    }

    // INSERT METHODS ==============================================================================

    public boolean insertGroup(int groupPosition, @NonNull M group){
        return insertGroup(groupPosition, group, new Listable<>());
    }

    public boolean insertGroup(int groupPosition, @NonNull M group, @NonNull Listable<M> childListable){
        if (groupListable.insert(groupPosition, group)){
            lastChildListable = childListableMap.put(group, childListable);
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean insertGroupList(int groupPosition, @NonNull ArrayList<M> groupList){
        if (groupListable.insertList(groupPosition, groupList)){
            for (M group : groupList){
                lastChildListable = childListableMap.put(group, new Listable<>());
            }
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean insertChild(@NonNull M group, int childPosition, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.insert(childPosition, child);
    }

    public boolean insertChildList(@NonNull M group, int childPosition, @NonNull ArrayList<M> childList){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.insertList(childPosition, childList);
    }

    // GET METHODS =================================================================================

    public Map<M, Listable<M>> getChildListableMap() {
        return childListableMap;
    }

    public Listable<M> getGroupListable() {
        return groupListable;
    }

    public ArrayList<M> getGroupList(){
        return groupListable.getList();
    }

    public ArrayList<M> getChildList(@NonNull M group){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.getList() : null;
    }

    public ArrayList<M> getLastChildList() {
        return lastChildListable != null ? lastChildListable.getList() : null;
    }

    public M getGroup(int groupPosition){
        return groupListable.get(groupPosition);
    }

    public M getChild(@NonNull M group, int childPosition){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.get(childPosition) : null;
    }

    public M getLastGroup(){
        return groupListable.getLast();
    }

    public M getLastChild(@NonNull M group){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.getLast() : null;
    }

    public long getGroupIdx(){
        return groupListable.getIdx();
    }

    public long getChildIdx(@NonNull M group){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.getIdx() : -1;
    }

    // SET METHODS =================================================================================

    public boolean setGroup(int groupPosition, @NonNull M group){
        if (groupListable.set(groupPosition, group)){
            lastChildListable = childListableMap.remove(groupListable.getLast());
            lastChildListable = childListableMap.put(group, lastChildListable);
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean setChild(@NonNull M group, int childPosition, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.set(childPosition, child);
    }

    public void setGroupIdx(long idx){
        groupListable.setIdx(idx);
    }

    public void setChildIdx(@NonNull M group, long idx){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null){
            childListable.setIdx(idx);
        }
    }

    // DELETE METHODS ==============================================================================

    public boolean deleteGroup(@NonNull M group){
        if (groupListable.delete(group)){
            lastChildListable = childListableMap.remove(group);
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean deleteChild(@NonNull M group, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.delete(child);
    }

    public boolean deleteGroupList(@NonNull ArrayList<M> groupList){
        if (groupListable.deleteList(groupList)){
            for (M group : groupList){
                lastChildListable = childListableMap.remove(group);
            }
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean deleteChildList(@NonNull M group, @NonNull ArrayList<M> childList){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.deleteList(childList);
    }

    // CONTAINS METHODS ============================================================================

    public boolean containsGroup(@NonNull M group){
        return groupListable.contains(group);
    }

    public boolean containsChild(@NonNull M group, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.contains(child);
    }

    public boolean containsGroupList(@NonNull ArrayList<M> groupList){
        return groupListable.containsList(groupList);
    }

    public boolean containsChildList(@NonNull M group, @NonNull ArrayList<M> childList){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.containsList(childList);
    }

    // RETAIN METHODS ==============================================================================

    public boolean retainGroupList(@NonNull ArrayList<M> groupList){
        if (groupListable.retainList(groupList)){
            Map<M, Listable<M>> childListableMap = new LinkedHashMap<>();
            for (M group : groupList){
                childListableMap.put(group, getChildListable(group));
            }
            this.childListableMap.clear();
            this.childListableMap.putAll(childListableMap);
            return true;
        }
        return false;
    }

    public boolean retainChildList(@NonNull M group, @NonNull ArrayList<M> childList){
        Listable<M> childListable = getChildListable(group);
        return childListable != null && childListable.retainList(childList);
    }

    // POSITION METHODS ============================================================================

    public int getGroupPosition(@NonNull M group){
        return groupListable.getPosition(group);
    }

    public int getChildPosition(@NonNull M group, @NonNull M child){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.getPosition(child) : Base.INVALID_POSITION;
    }

    // COUNT METHODS ===============================================================================

    public int getGroupCount(){
        return groupListable.getCount();
    }

    public int getChildCount(@NonNull M group){
        Listable<M> childListable = getChildListable(group);
        return childListable != null ? childListable.getCount() : Base.INVALID_POSITION;
    }

    // CLEAR METHODS ===============================================================================

    public void clearChildListable(@NonNull M group){
        Listable<M> childListable = getChildListable(group);
        if (childListable != null){
            childListable.clear();
        }
    }

    public void clearGroupListable(){
        childListableMap.clear();
        groupListable.clear();
    }

    // Experimental functions ======================================================================

    public boolean insertMapable(int groupPosition, @NonNull Mappable<M> mappable){
        ArrayList<M> groupList = mappable.getGroupList();
        if (groupListable.insertList(groupPosition, groupList)){
            for (M group : groupList){
                lastChildListable = childListableMap.put(group, mappable.getChildListable(group));
            }
            return true;
        }
        lastChildListable = null;
        return false;
    }

    public boolean addMapable(@NonNull Mappable<M> mappable){
        ArrayList<M> groupList = mappable.getGroupList();
        if (groupListable.appendList(groupList)){
            for (M group : groupList){
                lastChildListable = childListableMap.put(group, mappable.getChildListable(group));
            }
            return true;
        }
        lastChildListable = null;
        return false;
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private Listable<M> getChildListable(int groupPosition){
        return getChildListable(getGroup(groupPosition));
    }

    private Listable<M> getChildListable(@NonNull M group){
        return childListableMap.get(group);
    }
}
