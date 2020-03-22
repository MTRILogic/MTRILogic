package com.mtrilogic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.MapPaginable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableListener;

import java.util.ArrayList;
import java.util.Set;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private LayoutInflater inflater;
    private ExpandableListener listener;
    private MapPaginable<Modelable> paginable;

    private ArrayList<Listable<Modelable>> lastListableList;
    private Listable<Modelable> lastListable;
    //private Listable<Modelable> groupListable, lastListable;
    //private Mapable<Modelable> childMapable;

    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableAdapter(@NonNull Context context, @NonNull ExpandableListener listener,
                             @NonNull MapPaginable<Modelable> paginable, int groupTypeCount,
                             int childTypeCount){
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.paginable = paginable;
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
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            ArrayList<Modelable> modelableList = groupListable.getModelableList();
            if (modelableList != null){
                return modelableList.toArray(new Modelable[groupListable.getModelableCount()]);
            }
        }
        return null;
    }

    /**
     *
     * @param groupModelable The group's modelable key
     * @return The child modelable array
     */
    public final Modelable[] getChildModelableArray(@NonNull Modelable groupModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                ArrayList<Modelable> modelableList = childListable.getModelableList();
                if (modelableList != null){
                    return modelableList.toArray(new Modelable[childListable.getModelableCount()]);
                }
            }
        }
        return null;
    }

    // APPEND =================================================================

    /**
     * Append a group modelable's list to the group's listable
     * @param groupModelableList A list of group's modelables
     * @return True if the groupModelableList changed
     */
    public final boolean appendGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
                int count = 0;
                long idx = groupListable.getIdx();
                for(Modelable groupModelable : groupModelableList){
                    if(!groupListable.containsModelable(groupModelable) && groupListable.appendModelable(groupModelable)){
                        lastListable = childMapable.putListable(groupModelable, new Listable<>());
                        groupModelable.setItemId(idx);
                        count++;
                        idx++;
                    }
                }
                groupListable.setIdx(idx);
                return count > 0;
            }
        }
        return false;
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
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
                if(!groupListable.containsModelable(groupModelable) && groupListable.appendModelable(groupModelable)){
                    lastListable = childMapable.putListable(groupModelable, childListable);
                    long idx = groupListable.getIdx();
                    groupModelable.setItemId(idx);
                    groupListable.setIdx(++idx);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean appendChildModelableList(@NonNull Modelable groupModelable, @NonNull ArrayList<Modelable> childModelableList){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                int count = 0;
                long idx = childListable.getIdx();
                for(Modelable childModelable : childModelableList){
                    if(!childListable.containsModelable(childModelable) && childListable.appendModelable(childModelable)){
                        childModelable.setItemId(idx);
                        count++;
                        idx++;
                    }
                }
                childListable.setIdx(idx);
                return count > 0;
            }
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
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            if(childMapable.containsModelableKey(groupModelable)){
                Listable<Modelable> childListable = childMapable.getListable(groupModelable);
                if (childListable != null){
                    if (!childListable.containsModelable(childModelable) && childListable.appendModelable(childModelable)){
                        long idx = childListable.getIdx();
                        childModelable.setItemId(idx);
                        childListable.setIdx(++idx);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // INSERT =================================================================

    public final boolean insertGroupModelableList(int groupPosition, @NonNull ArrayList<Modelable> groupModelableList){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
                int count = 0;
                long idx = groupListable.getIdx();
                for(Modelable groupModelable : groupModelableList){
                    if(!groupListable.containsModelable(groupModelable) && groupListable.insertModelable(groupPosition, groupModelable)){
                        lastListable = childMapable.putListable(groupModelable, new Listable<>());
                        groupModelable.setItemId(idx);
                        count++;
                        idx++;
                    }
                }
                groupListable.setIdx(idx);
                return count > 0;
            }
        }
        return false;
    }

    public final boolean insertGroupModelable(int groupPosition, @NonNull Modelable groupModelable, @NonNull Listable<Modelable> childListable){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
                if(!groupListable.containsModelable(groupModelable) && groupListable.insertModelable(groupPosition, groupModelable)){
                    lastListable = childMapable.putListable(groupModelable, childListable);
                    long idx = groupListable.getIdx();
                    groupModelable.setItemId(idx);
                    groupListable.setIdx(++idx);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean insertChildModelableList(@NonNull Modelable groupModelable, int childPosition, @NonNull ArrayList<Modelable> childModelableList){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                int count = 0;
                long idx = childListable.getIdx();
                for(Modelable childModelable : childModelableList){
                    if(!childListable.containsModelable(childModelable) && childListable.insertModelable(childPosition, childModelable)){
                        childModelable.setItemId(idx);
                        count++;
                        idx++;
                    }
                }
                childListable.setIdx(idx);
                return count > 0;
            }
        }
        return false;

    }

    public final boolean insertChildModelable(@NonNull Modelable groupModelable, int childPosition, @NonNull Modelable childModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                if (!childListable.containsModelable(childModelable) && childListable.insertModelable(childPosition, childModelable)){
                    long idx = childListable.getIdx();
                    childModelable.setItemId(idx);
                    childListable.setIdx(idx);
                    return true;
                }
            }
        }
        return false;
    }

    // GET ====================================================================

    /**
     * Returns the current group modelable list
     * @return The current group modelable list
     */
    public final Listable<Modelable> getGroupListable(){
        return paginable.getGroupListable();
    }

    public final Listable<Modelable> getChildListable(@NonNull Modelable groupModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            return childMapable.getListable(groupModelable);
        }
        return null;
    }

    /**
     * Returns the current child modelable map
     * @return The current child modelable map
     */
    public final Mapable<Modelable> getChildMapable(){
        return paginable.getChildMapable();
    }

    public final Modelable getGroupModelable(int groupPosition){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            return groupListable.getModelable(groupPosition);
        }
        return null;
    }

    public final Modelable getChildModelable(@NonNull Modelable groupModelable, int childPosition){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                return childListable.getModelable(childPosition);
            }
        }
        return null;
    }

    // SET ====================================================================

    /**
     * Replaces the current group modelable list and
     * recreate a new child modelable map fro it
     * @param groupListable The new group modelable list
     */
    public final void setGroupListable(@NonNull Listable<Modelable> groupListable){
        paginable.setGroupListable(groupListable);
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            childMapable.reset();
            long idx = 0;
            for(Modelable groupModelable : groupListable.getModelableList()){
                lastListable = childMapable.putListable(groupModelable, new Listable<>());
                groupModelable.setItemId(idx);
                idx++;
            }
            groupListable.setIdx(idx);
        }
    }

    public final void setChildMapable(@NonNull Mapable<Modelable> childMapable){
        paginable.setChildMapable(childMapable);
        lastListableList.clear();
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            groupListable.reset();
            long idx = 0;
            Set<Modelable> modelableSet = childMapable.getListableMap().keySet();
            for (Modelable groupModelable : modelableSet){
                if (groupListable.appendModelable(groupModelable)){
                    groupModelable.setItemId(idx);
                    idx++;
                }else {
                    lastListable = childMapable.deleteListable(groupModelable);
                    lastListableList.add(lastListable);
                }
            }
            groupListable.setIdx(idx);
        }
    }

    /**
     * Replaces a group modelable with another in a given position
     * @param groupPosition The position where the group modelable will be replaced
     * @param groupModelable The replacement group modelable
     * @return The replaced group modelable or null
     */
    public final Modelable setGroupModelable(int groupPosition, @NonNull Modelable groupModelable){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            long idx = groupListable.getIdx();
            groupModelable.setItemId(idx);
            groupListable.setIdx(++idx);
            return groupListable.setModelable(groupPosition, groupModelable);
        }
        return null;
    }

    /**
     * Replace a child modelable with another in a given position
     * @param groupModelable The related groupModelable's itemId
     * @param childPosition The childModelable's position to set
     * @param childModelable The childModelable's replacement to set
     * @return The Replaced child modelable or null
     */
    public final Modelable setChildModelable(@NonNull Modelable groupModelable, int childPosition, @NonNull Modelable childModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                long idx = childListable.getIdx();
                childModelable.setItemId(idx);
                childListable.setIdx(++idx);
                return childListable.setModelable(childPosition, childModelable);
            }
        }
        return null;
    }

    // DELETE =================================================================

    public final boolean deleteGroupModelableList(@NonNull ArrayList<Modelable> groupModelableList){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
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
        }
        return false;
    }

    /**
     *
     * @param groupModelable the modelable to remove from list
     * @return True if group modelable was removed
     */
    public final boolean deleteGroupModelable(@NonNull Modelable groupModelable){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null){
                if(groupListable.deleteModelable(groupModelable)){
                    lastListable = childMapable.deleteListable(groupModelable);
                    lastListableList.add(lastListable);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean deleteChildModelableList(@NonNull Modelable groupModelable, @NonNull ArrayList<Modelable> childModelableList){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
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
        return false;
    }

    /**
     *
     * @param groupModelable The position of the group modelable key
     * @param childModelable The position of the child modelable to remove
     * @return True if the child modelable was removed
     */
    public final boolean deleteChildModelable(@NonNull Modelable groupModelable, @NonNull Modelable childModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                return childListable.deleteModelable(childModelable);
            }
        }
        return false;
    }

    // RESET ==================================================================

    /**
     * Cleans all the group modelable list
     * Therefore, clean all the child modelable map
     */
    public final void resetGroupListable(){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            Mapable<Modelable> childMapable = paginable.getChildMapable();
            if (childMapable != null) {
                groupListable.reset();
                childMapable.reset();
            }
        }
    }

    /**
     * Resets the childModelableList associated with the groupModelable's itemId
     * @param groupModelable The groupModelable's itemId
     */
    public final void resetChildListable(@NonNull Modelable groupModelable){
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null) {
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            if (childListable != null){
                childListable.reset();
            }
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public int getGroupCount(){
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            return groupListable.getModelableCount();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition){
        Listable<Modelable> childListable = getChildListable(groupPosition);
        if (childListable != null){
            return childListable.getModelableCount();
        }
        return 0;
    }

    @Override
    public Modelable getGroup(int groupPosition){
        // get modelable through modelableList's get method
        Listable<Modelable> groupListable = paginable.getGroupListable();
        if (groupListable != null){
            return groupListable.getModelable(groupPosition);
        }
        return null;
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        // get modelable through modelableList's get method
        Listable<Modelable> childListable = getChildListable(groupPosition);
        if (childListable != null){
            return childListable.getModelable(childPosition);
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
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean lastChild, View view, ViewGroup parent){
        Modelable childModelable = getChild(groupPosition, childPosition);
        if (childModelable != null){
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
        Mapable<Modelable> childMapable = paginable.getChildMapable();
        if (childMapable != null){
            return childMapable.getListable(getGroup(groupPosition));
        }
        return null;
    }
}
