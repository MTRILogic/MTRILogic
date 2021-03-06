package com.mtrilogic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private LayoutInflater inflater;
    private ExpandableListener listener;

    private Listable<Modelable> groupListable;
    private Mapable<Modelable> childMapable;

    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableAdapter(@NonNull Context context, @NonNull ExpandableListener listener,
                             @NonNull Listable<Modelable> groupListable,
                             @NonNull Mapable<Modelable> childMapable, int groupTypeCount, int childTypeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.groupListable = groupListable;
        this.childMapable = childMapable;
        setGroupTypeCount(groupTypeCount);
        setChildTypeCount(childTypeCount);
        stableIds = false;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void setGroupTypeCount(int groupTypeCount){
        groupTypeCount = groupTypeCount > 0 ? groupTypeCount : 1;
        this.groupTypeCount = groupTypeCount;
    }

    public final void setChildTypeCount(int childTypeCount){
        childTypeCount = childTypeCount > 0 ? childTypeCount : 1;
        this.childTypeCount = childTypeCount;
    }

    public final void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    // ARRAY ==================================================================

    /**
     * Devuelve la lista de grupos como un arreglo de modelables.
     * @return El arreglo a ser devuelto.
     */
    public final Modelable[] getGroupModelableListAsArray(){
        ArrayList<Modelable> list = groupListable.getList();
        if (list != null){
            return list.toArray(new Modelable[groupListable.getCount()]);
        }
        return null;
    }

    /**
     *
     * @param groupModelable The group's modelable key
     * @return The child modelable array
     */
    public final Modelable[] getChildModelableListAsArray(@NonNull Modelable groupModelable){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        if (childListable != null){
            ArrayList<Modelable> list = childListable.getList();
            if (list != null){
                return list.toArray(new Modelable[childListable.getCount()]);
            }
        }
        return null;
    }

    // APPEND ======================================================================================

    /**
     * Append a group modelable's list to the group's listable
     * @param groupModelableList A list of group's modelables
     * @return True if the groupModelableList changed
     */
    public final boolean appendGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        int count = 0;
        for (Modelable groupModelable: groupModelableList){
            if (!groupListable.contains(groupModelable) && groupListable.append(groupModelable)){
                if (childMapable.putListable(groupModelable, new Listable<>())){
                    count++;
                }
            }
        }
        return count > 0;
    }

    /**
     * add a new group model to the group model list and
     * add a new group key with the given child model list
     * to the child model map
     * @param groupModelable The group modelable to add
     * @param childListable The child modelable list
     * @return True if group modelable was added
     */
    public final boolean appendGroupModelable(@NonNull Modelable groupModelable, @NonNull Listable<Modelable> childListable){
        if (!groupListable.contains(groupModelable) && groupListable.append(groupModelable)){
            return childMapable.putListable(groupModelable, childListable);
        }
        return false;
    }

    public final boolean appendChildModelableList(@NonNull Modelable groupModelable, @NonNull ArrayList<Modelable> childModelableList){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        if (childListable != null){
            int count = 0;
            for (Modelable childModelable : childModelableList){
                if (!childListable.contains(childModelable) && childListable.append(childModelable)){
                    count++;
                }
            }
            return count > 0;
        }
        return false;
    }

    /**
     * append a new childModelable to end of childModelableList
     *      associated with the given groupModelable's itemId
     * @param groupModelable The groupModelable's itemId
     * @param childModelable The childModelable to append
     * @return True if the childModelable was appended to the list
     */
    public final boolean appendChildModelable(@NonNull Modelable groupModelable, @NonNull Modelable childModelable){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        if (childListable != null){
            return !childListable.contains(childModelable) && childListable.append(childModelable);
        }
        return false;
    }

    // INSERT ======================================================================================

    public final boolean insertGroupModelableList(int groupPosition, @NonNull ArrayList<Modelable> groupModelableList){
        int count = 0;
        for (Modelable groupModelable : groupModelableList){
            if (!groupListable.contains(groupModelable) && groupListable.insert(groupPosition, groupModelable)){
                if (childMapable.putListable(groupModelable, new Listable<>())){
                    count++;
                }
            }
        }
        return count > 0;
    }

    public final boolean insertGroupModelable(int groupPosition, @NonNull Modelable groupModelable, @NonNull Listable<Modelable> childListable){
        if (!groupListable.contains(groupModelable) && groupListable.insert(groupPosition, groupModelable)){
            return childMapable.putListable(groupModelable, childListable);
        }
        return false;
    }

    public final boolean insertChildModelableList(@NonNull Modelable groupModelable, int childPosition, @NonNull ArrayList<Modelable> childModelableList){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        if (childListable != null){
            int count = 0;
            for (Modelable childModelable : childModelableList){
                if (!childListable.contains(childModelable) && childListable.insert(childPosition, childModelable)){
                    count++;
                }
            }
            return count > 0;
        }
        return false;

    }

    public final boolean insertChildModelable(@NonNull Modelable groupModelable, int childPosition, @NonNull Modelable childModelable){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        if (childListable != null){
            return !childListable.contains(childModelable) && childListable.insert(childPosition, childModelable);
        }
        return false;
    }

    // GET =========================================================================================

    /**
     * Returns the current group modelable list
     * @return The current group modelable list
     */
    public final Listable<Modelable> getGroupListable(){
        return groupListable;
    }

    public final Listable<Modelable> getChildListable(@NonNull Modelable groupModelable){
        return childMapable.getListable(groupModelable);
    }

    /**
     * Returns the current child modelable map
     * @return The current child modelable map
     */
    public final Mapable<Modelable> getChildMapable(){
        return childMapable;
    }

    public final Modelable getGroupModelable(int groupPosition){
        return groupListable.get(groupPosition);
    }

    public final Modelable getChildModelable(@NonNull Modelable groupModelable, int childPosition){
        Listable<Modelable> childListable = getChildListable(groupModelable);
        if (childListable != null){
            return childListable.get(childPosition);
        }
        return null;
    }

    public final Listable<Modelable> getLastListable() {
        return childMapable.getLastListable();
    }

    public final Modelable getLastModelable() {
        return groupListable.getLastItem();
    }

    // SET =========================================================================================

    /**
     * Replaces the current group modelable list and
     * recreate a new child modelable map fro it
     * @param groupListable The new group modelable list
     */
    public final boolean setGroupListable(@NonNull Listable<Modelable> groupListable){
        if (this.groupListable != groupListable){
            ArrayList<Modelable> groupModelableList = groupListable.getList();
            if (groupModelableList != null){
                this.groupListable = groupListable;
                childMapable.getListableMap().clear();
                int count = 0;
                for (Modelable groupModelable : groupModelableList){
                    if (childMapable.putListable(groupModelable, new Listable<>())){
                        count++;
                    }
                }
                return count > 0;
            }
        }
        return false;
    }

    public final boolean setChildMapable(@NonNull Mapable<Modelable> childMapable){
        Map<Modelable, Listable<Modelable>> listableMap = childMapable.getListableMap();
        if (listableMap != null){
            Set<Modelable> modelableSet = listableMap.keySet();
            groupListable.reset();
            int count = 0;
            for (Modelable groupModelable : modelableSet){
                if (groupListable.append(groupModelable)){
                    count++;
                }
            }
            return count > 0;
        }
        return false;
    }

    /**
     * Replaces a group modelable with another in a given position
     * @param groupPosition The position where the group modelable will be replaced
     * @param groupModelable The replacement group modelable
     * @return The replaced group modelable or null
     */
    public final boolean setGroupModelable(int groupPosition, @NonNull Modelable groupModelable){
        return groupListable.set(groupPosition, groupModelable);
    }

    /**
     * Replace a child modelable with another in a given position
     * @param groupModelable The related groupModelable's itemId
     * @param childPosition The childModelable's position to set
     * @param childModelable The childModelable's replacement to set
     * @return The Replaced child modelable or null
     */
    public final boolean setChildModelable(@NonNull Modelable groupModelable, int childPosition,
                                             @NonNull Modelable childModelable){
        Listable<Modelable> childListable = getChildListable(groupModelable);
        if (childListable != null){
            return childListable.set(childPosition, childModelable);
        }
        return false;
    }

    // DELETE ======================================================================================

    public final boolean deleteGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        int size = groupModelableList.size();
        if (size > 0) {
            int count = size;
            for (Modelable groupModelable : groupModelableList) {
                if (groupListable.delete(groupModelable) && childMapable.deleteListable(groupModelable)) {
                    count--;
                }
            }
            return count < size;
        }
        return false;
    }

    /**
     *
     * @param groupModelable the modelable to remove from list
     * @return True if group modelable was removed
     */
    public final boolean deleteGroupModelable(@NonNull Modelable groupModelable){
        return groupListable.delete(groupModelable) && childMapable.deleteListable(groupModelable);
    }

    public final boolean deleteChildModelableList(@NonNull Modelable groupModelable,
                                                  @NonNull ArrayList<Modelable> childModelableList){
        int size = childModelableList.size();
        if (size > 0) {
            Listable<Modelable> childListable = getChildListable(groupModelable);
            if (childListable != null){
                int count = size;
                for (Modelable childModelable : childModelableList){
                    if (childListable.delete(childModelable)){
                        count--;
                    }
                }
                return count < size;
            }
        }
        return false;
    }

    /**
     *
     * @param groupModelable The position of the group modelable key
     * @param childModelable The position of the child modelable to remove
     * @return True if the child modelable was removed
     */
    public final boolean deleteChildModelable(@NonNull Modelable groupModelable,
                                              @NonNull Modelable childModelable){
        Listable<Modelable> childListable = getChildListable(groupModelable);
        if (childListable != null){
            return childListable.delete(childModelable);
        }
        return false;
    }

    // RESET =======================================================================================

    /**
     * Cleans all the group modelable list
     * Therefore, clean all the child modelable map
     */
    public final void resetGroupListable(){
        groupListable.reset();
        childMapable.getListableMap().clear();
    }

    /**
     * Resets the childModelableList associated with the groupModelable's itemId
     * @param groupModelable The groupModelable's itemId
     */
    public final void resetChildListable(@NonNull Modelable groupModelable){
        Listable<Modelable> childListable = getChildListable(groupModelable);
        if (childListable != null){
            childListable.reset();
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getGroupCount(){
        return groupListable.getCount();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        Listable<Modelable> childListable = getChildListable(groupPosition);
        if (childListable != null){
            return childListable.getCount();
        }
        return 0;
    }

    @Override
    public Modelable getGroup(int groupPosition){
        // get modelable through modelableList's get method
        return groupListable.get(groupPosition);
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        // get modelable through modelableList's get method
        Listable<Modelable> childListable = getChildListable(groupPosition);
        if (childListable != null){
            return childListable.get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition){
        Modelable groupModelable = getGroup(groupPosition);
        if (groupModelable != null){
            return groupModelable.getItemId();
        }
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        Modelable childModelable = getChild(groupPosition, childPosition);
        if (childModelable != null){
            return childModelable.getItemId();
        }
        return 0;
    }

    @Override
    public boolean hasStableIds(){
        return stableIds;
    }

    @Override
    public View getGroupView(int groupPosition, boolean expanded, View view, ViewGroup parent){
        Modelable groupModelable = getGroup(groupPosition);
        if (groupModelable != null){
            ExpandableGroup<?, ?> expandableGroup;
            if(view != null){
                expandableGroup = (ExpandableGroup<?, ?>)view.getTag();
            }else {
                int viewType = groupModelable.getViewType();
                expandableGroup = listener.getExpandableGroup(viewType, inflater, parent);
                view = expandableGroup.getItemView();
                view.setTag(expandableGroup);
            }
            expandableGroup.bindModel(groupModelable, groupPosition, expanded);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean lastChild, View view, ViewGroup parent){
        Modelable childModelable = getChild(groupPosition, childPosition);
        if (childModelable != null){
            ExpandableChild<?, ?> expandableChild;
            if(view != null){
                expandableChild = (ExpandableChild<?, ?>)view.getTag();
            }else {
                int viewType = childModelable.getViewType();
                expandableChild = listener.getExpandableChild(viewType, inflater, parent);
                view = expandableChild.getItemView();
                view.setTag(expandableChild);
            }
            expandableChild.bindModel(childModelable, groupPosition, childPosition, lastChild);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        Modelable childModelable = getChild(groupPosition, childPosition);
        if (childModelable != null){
            return childModelable.isEnabled();
        }
        return false;
    }

    @Override
    public int getGroupType(int groupPosition){
        Modelable groupModelable = getGroup(groupPosition);
        if (groupModelable != null){
            return groupModelable.getViewType();
        }
        return 0;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition){
        Modelable childModelable = getChild(groupPosition, childPosition);
        if (childModelable != null){
            return childModelable.getViewType();
        }
        return 0;
    }

    @Override
    public int getGroupTypeCount(){
        return groupTypeCount;
    }

    @Override
    public int getChildTypeCount(){
        return childTypeCount;
    }

    @Override
    public long getCombinedChildId(long groupId, long childId){
        return 0x7000000000000000L | ((groupId & 0x7FFFFFFF) << 32) | childId;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    // ================< PRIVATE METHODS >==========================================================

    private Listable<Modelable> getChildListable(int groupPosition){
        return childMapable.getListable(getGroup(groupPosition));
    }
}
