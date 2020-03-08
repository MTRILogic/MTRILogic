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

import androidx.annotation.NonNull;

@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private LayoutInflater inflater;
    private ExpandableListener listener;
    private ArrayList<Listable<Modelable>> lastListableList;
    private Listable<Modelable> groupListable, lastListable;
    private Mapable<Modelable> childMapable;
    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableAdapter(@NonNull Context context, @NonNull ExpandableListener listener,
                             @NonNull Listable<Modelable> groupListable,
                             @NonNull Mapable<Modelable> childMapable, int groupTypeCount,
                             int childTypeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.groupListable = groupListable;
        this.childMapable = childMapable;
        setGroupTypeCount(groupTypeCount);
        setChildTypeCount(childTypeCount);
        lastListableList = new ArrayList<>();
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

    public final ArrayList<Listable<Modelable>> getLastListableList() {
        return lastListableList;
    }

    // ARRAY ==================================================================

    public final Modelable[] getGroupModelableArray(){
        return groupListable.getModelableList().toArray(new Modelable[groupListable.getModelableCount()]);
    }

    /**
     *
     * @param groupModelable The group's modelable key
     * @return The child modelable array
     */
    public final Modelable[] getChildModelableArray(@NonNull Modelable groupModelable){
        Listable<Modelable> childListable = childMapable.getListable(groupModelable);
        return childListable.getModelableList().toArray(new Modelable[childListable.getModelableCount()]);
    }

    // APPEND =================================================================

    /**
     * Append a group modelable's list to the group's listable
     * NOTE: each modelable should have obtained its itemId
     * from the listable's idx where it will be attached
     * @param groupModelableList A list of group's modelables
     * @return True if the groupModelableList changed
     */
    public final boolean appendGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        int count = 0;
        for(Modelable groupModelable : groupModelableList){
            if(!groupListable.containsModelable(groupModelable) && groupListable.appendModelable(groupModelable)){
                childMapable.putListable(groupModelable, new Listable<>());
                count++;
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
        if(!groupListable.containsModelable(groupModelable) && groupListable.appendModelable(groupModelable)){
            lastListable = childMapable.putListable(groupModelable, childListable);
            return true;
        }
        return false;
    }

    public final boolean appendChildModelableList(@NonNull Modelable groupModelable, @NonNull ArrayList<Modelable> childModelableList){
        int count = 0;
        if(childMapable.containsModelableKey(groupModelable)){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            for(Modelable childModelable : childModelableList){
                if(!childListable.containsModelable(childModelable) && childListable.appendModelable(childModelable)){
                    count++;
                }
            }
        }
        return count > 0;
    }

    /**
     * append a new childModelable to end of childModelableList
     *      associated with the given groupModelable's itemId
     * @param groupModelable The groupModelable's itemId
     * @param childModelable The childModelable to append
     * @return True if the childModelable was appended to the list
     */
    public final boolean appendChildModelable(@NonNull Modelable groupModelable, @NonNull Modelable childModelable){
        if(childMapable.containsModelableKey(groupModelable)){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            return !childListable.containsModelable(childModelable) && childListable.appendModelable(childModelable);
        }
        return false;
    }

    // INSERT =================================================================

    public final boolean insertGroupModelableList(int groupPosition, @NonNull ArrayList<Modelable> groupModelableList){
        int count = 0;
        for(Modelable groupModelable : groupModelableList){
            if(!groupListable.containsModelable(groupModelable) && groupListable.insertModelable(groupPosition, groupModelable)){
                lastListable = childMapable.putListable(groupModelable, new Listable<>());
                count++;
            }
        }
        return count > 0;
    }

    public final boolean insertGroupModelable(int groupPosition, @NonNull Modelable groupModelable, @NonNull Listable<Modelable> childListable){
        if(!groupListable.containsModelable(groupModelable) && groupListable.insertModelable(groupPosition, groupModelable)){
            lastListable = childMapable.putListable(groupModelable, childListable);
            return true;
        }
        return false;
    }

    public final boolean insertChildModelableList(@NonNull Modelable groupModelable, int childPosition, @NonNull ArrayList<Modelable> childModelableList){
        int count = 0;
        if(childMapable.containsModelableKey(groupModelable)){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            for(Modelable childModelable : childModelableList){
                if(!childListable.containsModelable(childModelable) && childListable.insertModelable(childPosition, childModelable)){
                    count++;
                }
            }
        }
        return count > 0;
    }

    public final boolean insertChildModelable(@NonNull Modelable groupModelable, int childPosition, @NonNull Modelable childModelable){
        if(childMapable.containsModelableKey(groupModelable)){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            return !childListable.containsModelable(childModelable) && childListable.insertModelable(childPosition, childModelable);
        }
        return false;
    }

    // GET ====================================================================

    /**
     * Returns the current group modelable list
     * @return The current group modelable list
     */
    public final Listable<Modelable> getGroupListable(){
        return groupListable;
    }

    public final Listable<Modelable> getChildListable(@NonNull Modelable groupModelable){
        return childMapable.containsModelableKey(groupModelable) ? childMapable.getListable(groupModelable) : null;
    }

    /**
     * Returns the current child modelable map
     * @return The current child modelable map
     */
    public final Mapable<Modelable> getChildMapable(){
        return childMapable;
    }

    public final Modelable getGroupModelable(int groupPosition){
        return groupListable.getModelable(groupPosition);
    }

    public final Modelable getChildModelable(@NonNull Modelable groupModelable, int childPosition){
        return childMapable.containsModelableKey(groupModelable) ? childMapable.getListable(groupModelable).getModelable(childPosition) : null;
    }

    // SET ====================================================================

    /**
     * Replaces the current group modelable list and
     * recreate a new child modelable map fro it
     * @param groupListable The new group modelable list
     */
    public final void setGroupListable(@NonNull Listable<Modelable> groupListable){
        this.groupListable = groupListable;
        childMapable.reset();
        for(Modelable groupModelable : groupListable.getModelableList()){
            lastListable = childMapable.putListable(groupModelable, new Listable<>());
        }
    }

    public final void setChildMapable(@NonNull Mapable<Modelable> childMapable){
        this.childMapable = childMapable;
        lastListableList.clear();
        groupListable.reset();
        for(Map.Entry<Modelable, Listable<Modelable>> childEntry : childMapable.getListableMap().entrySet()){
            Modelable groupModelable = childEntry.getKey();
            if(!groupListable.appendModelable(groupModelable)){
                lastListable = childMapable.deleteListable(groupModelable);
                lastListableList.add(lastListable);
            }
        }
    }

    /**
     * Replaces a group modelable with another in a given position
     * @param groupPosition The position where the group modelable will be replaced
     * @param groupModelable The replacement group modelable
     * @return The replaced group modelable or null
     */
    public final Modelable setGroupModelable(int groupPosition, @NonNull Modelable groupModelable){
        return groupListable.setModelable(groupPosition, groupModelable);
    }

    /**
     * Replace a child modelable with another in a given position
     * @param groupModelable The related groupModelable's itemId
     * @param childPosition The childModelable's position to set
     * @param childModelable The childModelable's replacement to set
     * @return The Replaced child modelable or null
     */
    public final Modelable setChildModelable(@NonNull Modelable groupModelable, int childPosition, @NonNull Modelable childModelable){
        return childMapable.containsModelableKey(groupModelable) ? childMapable.getListable(childModelable).setModelable(childPosition, childModelable) : null;
    }

    // DELETE =================================================================

    public final boolean deleteGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        int count = groupModelableList.size();
        lastListableList.clear();
        for(Modelable groupModelable : groupModelableList){
            if(groupListable.deleteModelable(groupModelable)){
                lastListable = childMapable.deleteListable(groupModelable);
                lastListableList.add(lastListable);
                count--;
            }
        }
        return count == 0;
    }

    /**
     *
     * @param groupModelable the modelable to remove from list
     * @return True if group modelable was removed
     */
    public final boolean deleteGroupModelable(@NonNull Modelable groupModelable){
        if(groupListable.deleteModelable(groupModelable)){
            lastListable = childMapable.deleteListable(groupModelable);
            lastListableList.add(lastListable);
            return true;
        }
        return false;
    }

    public final boolean deleteChildModelableList(@NonNull Modelable groupModelable, @NonNull ArrayList<Modelable> childModelableList){
        int count = childModelableList.size();
        if(childMapable.containsModelableKey(groupModelable)){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            for(Modelable childModelable : childModelableList){
                if(childListable.deleteModelable(childModelable)){
                    count--;
                }
            }
        }
        return count == 0;
    }

    /**
     *
     * @param groupModelable The position of the group modelable key
     * @param childModelable The position of the child modelable to remove
     * @return True if the child modelable was removed
     */
    public final boolean deleteChildModelable(@NonNull Modelable groupModelable, @NonNull Modelable childModelable){
        if(childMapable.containsModelableKey(groupModelable)){
            return childMapable.getListable(groupModelable).deleteModelable(childModelable);
        }
        return false;
    }

    // RESET ==================================================================

    /**
     * Cleans all the group modelable list
     * Therefore, clean all the child modelable map
     */
    public final void resetGroupListable(){
        groupListable.reset();
        childMapable.reset();
    }

    /**
     * Resets the childModelableList associated with the groupModelable's itemId
     * @param groupModelable The groupModelable's itemId
     */
    public final void resetChildListable(@NonNull Modelable groupModelable){
        if(childMapable.containsModelableKey(groupModelable)){
            childMapable.getListable(groupModelable).reset();
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getGroupCount(){
        return groupListable.getModelableCount();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return getChildListable(groupPosition).getModelableCount();
    }

    @Override
    public Modelable getGroup(int groupPosition){
        // get modelable through modelableList's get method
        return groupListable.getModelableList().get(groupPosition);
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        // get modelable through modelableList's get method
        return getChildListable(groupPosition).getModelableList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return getGroup(groupPosition).getItemId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return getChild(groupPosition, childPosition).getItemId();
    }

    @Override
    public boolean hasStableIds(){
        return stableIds;
    }

    @Override
    public View getGroupView(int groupPosition, boolean expanded, View view, ViewGroup parent){
        Modelable groupModelable = getGroup(groupPosition);
        ExpandableGroup expandableGroup;
        if(view != null){
            expandableGroup = (ExpandableGroup)view.getTag();
        }else {
            int viewType = groupModelable.getViewType();
            expandableGroup = listener.getExpandableGroup(viewType, inflater, parent);
            view = expandableGroup.getItemView();
            view.setTag(expandableGroup);
        }
        expandableGroup.bindHolder(groupModelable, groupPosition, expanded);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean lastChild, View view, ViewGroup parent){
        Modelable childModelable = getChild(groupPosition, childPosition);
        ExpandableChild expandableChild;
        if(view != null){
            expandableChild = (ExpandableChild)view.getTag();
        }else {
            int viewType = childModelable.getViewType();
            expandableChild = listener.getExpandableChild(viewType, inflater, parent);
            view = expandableChild.getItemView();
            view.setTag(expandableChild);
        }
        expandableChild.bindHolder(childModelable, groupPosition, childPosition, lastChild);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return getChild(groupPosition, childPosition).isEnabled();
    }

    @Override
    public int getGroupType(int groupPosition){
        return getGroup(groupPosition).getViewType();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition){
        return getChild(groupPosition, childPosition).getViewType();
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

    @NonNull
    private Listable<Modelable> getChildListable(int groupPosition){
        return childMapable.getListable(getGroup(groupPosition));
    }
}
