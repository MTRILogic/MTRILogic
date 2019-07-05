package com.mtrilogic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.ExpandableListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private static final String TAG = "ExpandableAdapter", LIST = "list", IDX = "idx";
    private ExpandableListener listener;
    private Listable<Modelable> groupModelableList;
    private LinkedHashMap<Long, Listable<Modelable>> childModelableMap;
    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public ExpandableAdapter(ExpandableListener listener, Listable<Modelable> groupModelableList, LinkedHashMap<Long, Listable<Modelable>> childModelableMap, int groupTypeCount, int childTypeCount){
        this.listener = listener;
        this.groupModelableList = groupModelableList;
        this.childModelableMap = childModelableMap;
        setGroupTypeCount(groupTypeCount);
        setChildTypeCount(childTypeCount);
        stableIds = false;
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

    public void setGroupTypeCount(int groupTypeCount){
        groupTypeCount = groupTypeCount > 0 ? groupTypeCount : 1;
        this.groupTypeCount = groupTypeCount;
    }

    public void setChildTypeCount(int childTypeCount){
        childTypeCount = childTypeCount > 0 ? childTypeCount : 1;
        this.childTypeCount = childTypeCount;
    }

    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    public Modelable[] getGroupModelableArray(){
        return groupModelableList.list.toArray(new Modelable[getGroupCount()]);
    }

    public Modelable[] getChildModelableArray(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            Listable<Modelable> childModelableList = getChildList(groupPosition);
            int size = childModelableList.list.size();
            return childModelableList.list.toArray(new Modelable[size]);
        }return null;
    }

    /**
     * Returns the current group modelable list
     * @return The current group modelable list
     */
    public Listable<Modelable> getGroupModelableList(){
        return groupModelableList;
    }

    /**
     * Replaces the current group modelable list and
     * recreate a new child modelable map fro it
     * @param groupModelableList The new group modelable list
     */
    public void setGroupModelableList(Listable<Modelable> groupModelableList){
        this.groupModelableList = groupModelableList;
        childModelableMap.clear();
        for(Modelable groupModelable : groupModelableList.list){
            childModelableMap.put(groupModelable.getItemId(), new Listable<>(new ArrayList<Modelable>()));
        }
    }

    /**
     * Returns the current child modelable map
     * @return The current child modelable map
     */
    public LinkedHashMap<Long, Listable<Modelable>> getChildModelableMap(){
        return childModelableMap;
    }

    //returns the child model list associated to the given group position
    public Listable<Modelable> getChildModelableList(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            return getChildList(groupPosition);
        }
        return null;
    }

    //replaces the child model list associated with a given group position
    public void setChildModelableList(int groupPosition, Listable<Modelable> childModelableList){
        if(isValidGroupPosition(groupPosition)){
            childModelableMap.put(getGroup(groupPosition).getItemId(), childModelableList);
        }
    }

    public Modelable getGroupModelable(int position){
        return isValidGroupPosition(position) ? getGroup(position) : null;
    }

    /**
     * Replaces a group modelable with another in a given position
     * @param groupPosition The position where the group modelable will be replaced
     * @param groupModelable The replacement group modelable
     * @return The replaced group modelable or null
     */
    public Modelable setGroupModelable(int groupPosition, Modelable groupModelable){
        return isValidGroupPosition(groupPosition) ? groupModelableList.list.set(groupPosition, groupModelable) : null;
    }

    public Modelable getChildModelable(int groupPosition, int childPosition){
        if(isValidGroupPosition(groupPosition)){
            Listable<Modelable> childModelableList = getChildList(groupPosition);
            if(isValidChildPosition(childPosition, childModelableList)){
                return childModelableList.list.get(childPosition);
            }
        }return null;
    }

    /**
     * Replace a child modelable with another in a given position
     * @param groupPosition The position of the group modelable that containts it
     * @param childPosition The position of the child modelable to replace
     * @param childModelable The replacement child modelable
     * @return The Replaced child modelable or null
     */
    public Modelable setChildModelable(int groupPosition, int childPosition, Modelable childModelable){
        if(isValidGroupPosition(groupPosition)){
            Listable<Modelable> childModelableList = getChildList(groupPosition);
            if(isValidChildPosition(childPosition,childModelableList)){
                return childModelableList.list.set(childPosition, childModelable);
            }
        }return null;
    }

    /**
     * add a new group model to the group model list and
     * add a new group key with an empty child model list value
     * to the child model map
     * @param groupModelable The group modelable to add
     * @return True if group modelable was added
    */
    public boolean addGroupModelable(Modelable groupModelable){
        return addGroupModelable(groupModelable, new Listable<>(new ArrayList<Modelable>()));
    }

    /**
     * add a new group model to the group model list and
     * add a new group key with the given child model list
     * to the child model map
     * @param groupModelable The group modelable to add
     * @param childModelableList The child modelable list
     * @return True if group modelable was added
     */
    public boolean addGroupModelable(Modelable groupModelable, Listable<Modelable> childModelableList){
        if(!groupModelableList.list.contains(groupModelable)){
            if(groupModelableList.list.add(groupModelable)){
                childModelableMap.put(groupModelable.getItemId(), childModelableList);
                return true;
            }
        }return false;
    }

    /**
     * add a new child modelable to the child modelable list
     *      associated with the given group position
     * @param groupPosition The position of the group modelable in the list
     * @param childModelable The modelable to add to the child modelable list
     * @return True if the child modelable was added
     */
    public boolean addChildModelable(int groupPosition, Modelable childModelable){
        if(isValidGroupPosition(groupPosition)){
            Listable<Modelable> childModelableList = getChildList(groupPosition);
            if(!childModelableList.list.contains(childModelable)){
                return childModelableList.list.add(childModelable);
            }
        }return false;
    }

    /**
     *
     * @param groupModelable the modelable to remove from list
     * @return True if group modelable was removed
     */
    public boolean removeGroupModelable(Modelable groupModelable){
        childModelableMap.remove(groupModelable.getItemId());
        return groupModelableList.list.remove(groupModelable);
    }

    /**
     *
     * @param groupPosition The position of the group modelable key
     * @param childPosition The position of the child modelable to remove
     * @return True if the child modelable was removed
     */
    public boolean removeChildModelable(int groupPosition, int childPosition){
        if(isValidGroupPosition(groupPosition)){
            Listable<Modelable> childModelableList = getChildList(groupPosition);
            if(isValidChildPosition(childPosition, childModelableList)){
                return childModelableList.list.remove(childModelableList.list.get(childPosition));
            }
        }return false;
    }

    /**
     * Cleans all the group modelable list
     * Therefore, clean all the child modelable map
     */
    public void clearGroupModelableList(){
        groupModelableList.list.clear();
        childModelableMap.clear();
    }

    /**
     * Cleans all the child modelable list associated with the modelable group
     * @param groupPosition index of the group modelable key
     */
    public void clearChildModelables(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            getChildList(groupPosition).list.clear();
        }
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public int getGroupCount(){
        return groupModelableList.list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return getChildList(groupPosition).list.size();
    }

    @Override
    public Modelable getGroup(int groupPosition){
        return groupModelableList.list.get(groupPosition);
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        return getChildList(groupPosition).list.get(childPosition);
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
            expandableGroup = listener.getExpandableGroup(viewType);
            view = expandableGroup.getInflatableView(parent);
        }
        expandableGroup.onBindHolder(groupModelable, groupPosition, expanded);
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
            expandableChild = listener.getExpandableChild(viewType);
            view = expandableChild.getInflatableView(parent);
        }
        expandableChild.onBindHolder(childModelable, groupPosition, childPosition, lastChild);
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

    // ++++++++++++++++| PRIVATE METHODS |+++++++++++++++++++++++++++++++++++++++++

    private Listable<Modelable> getChildList(int groupPosition){
        return getChildList(getGroup(groupPosition).getItemId());
    }

    private Listable<Modelable> getChildList(long groupId){
        return childModelableMap.get(groupId);
    }

    private boolean isValidGroupPosition(int groupPosition){
        return groupPosition > Base.INVALID_POSITION && groupPosition < groupModelableList.list.size();
    }

    private boolean isValidChildPosition(int childPosition, Listable<Modelable> childModelableList){
        return childPosition > Base.INVALID_POSITION && childPosition < childModelableList.list.size();
    }
}