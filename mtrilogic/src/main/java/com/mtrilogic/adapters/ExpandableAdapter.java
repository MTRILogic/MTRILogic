package com.mtrilogic.adapters;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtrilogic.abstracts.Expandable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private static final String TAG = "ExpandableAdapterTAGY";
    private static final String MODELABLE_LIST = "modelableList";
    private static final int NO_ITEMS = -1;
    private ExpandableListener childListener, groupListener;
    private ArrayList<Modelable> groupModelableList;
    private Map<Modelable,ArrayList<Modelable>> childModelableMap;
    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener){
        this(groupListener,childListener,1,1);
    }

    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, int groupTypeCount, int childTypeCount){
        this(groupListener,childListener,new ArrayList<Modelable>(),new HashMap<Modelable, ArrayList<Modelable>>(),groupTypeCount,childTypeCount);
    }

    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, ArrayList<Modelable> groupModelableList, Map<Modelable,ArrayList<Modelable>> childModelableMap){
        this(groupListener,childListener,groupModelableList,childModelableMap,1,1);
    }

    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, ArrayList<Modelable> groupModelableList, Map<Modelable,ArrayList<Modelable>> childModelableMap, int groupTypeCount, int childTypeCount){
        this.groupListener = groupListener;
        this.childListener = childListener;
        this.groupModelableList = groupModelableList;
        this.childModelableMap = childModelableMap;
        groupTypeCount = groupTypeCount > 0 ? groupTypeCount : 1;
        this.groupTypeCount = groupTypeCount;
        childTypeCount = childTypeCount > 0 ? childTypeCount : 1;
        this.childTypeCount = childTypeCount;
        stableIds = true;
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    public Modelable[] getGroupModelables(){
        return groupModelableList.toArray(new Modelable[getGroupCount()]);
    }

    public Modelable[] getChildModelables(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            ArrayList<Modelable> childModelableList = getChildList(groupPosition);
            int size = childModelableList.size();
            return childModelableList.toArray(new Modelable[size]);
        }return null;
    }

    /**
     * Replaces the current group modelable list and
     * recreate a new child modelable map fro it
     * @param groupModelableList The new group modelable list
     */
    public void setGroupModelableList(ArrayList<Modelable> groupModelableList){
        this.groupModelableList = groupModelableList;
        childModelableMap.clear();
        for(Modelable groupModelable : groupModelableList){
            childModelableMap.put(groupModelable,new ArrayList<Modelable>());
        }
    }

    /**
     * Returns the current group modelable list
     * @return The current group modelable list
     */
    public ArrayList<Modelable> getGroupModelableList(){
        return groupModelableList;
    }

    /**
     * Replaces the current child modelable map for a new child modelable map
     * Note that it create a new group modelable list from the key-set of the child model map
     * @param childModelableMap The new child modelable map
     */
    public void setChildModelableMap(Map<Modelable,ArrayList<Modelable>> childModelableMap){
        this.childModelableMap = childModelableMap;
        groupModelableList.clear();
        groupModelableList.addAll(childModelableMap.keySet());
    }

    /**
     * Returns the current child modelable map
     * @return The current child modelable map
     */
    public Map<Modelable,ArrayList<Modelable>> getChildModelableMap(){
        return childModelableMap;
    }

    //replaces the child model list associated with a given group position
    public void setChildModelableList(int groupPosition, ArrayList<Modelable> childModelableList){
        if(isValidGroupPosition(groupPosition)){
            childModelableMap.put(getGroup(groupPosition), childModelableList);
        }
    }

    //returns the child model list associated to the given group position
    public ArrayList<Modelable> getChildModelableList(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            return getChildList(groupPosition);
        }
        return null;
    }

    /**
     * Replaces a group modelable with another in a given position
     * @param groupPosition The position where the group modelable will be replaced
     * @param groupModelable The replacement group modelable
     * @return The replaced group modelable or null
     */
    public Modelable setGroupModelable(int groupPosition, Modelable groupModelable){
        return isValidGroupPosition(groupPosition) ? groupModelableList.set(groupPosition, groupModelable) : null;
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
            ArrayList<Modelable> childModelableList = getChildList(groupPosition);
            if(isValidChildPosition(childPosition,childModelableList)){
                return childModelableList.set(childPosition,childModelable);
            }
        }
        return null;
    }

    /**
     * add a new group model to the group model list and
     * add a new group key with an empty child model list value
     * to the child model map
     * @param groupModelable The group modelable to add
     * @return True if group modelable was added
    */
    public boolean addGroupModelable(Modelable groupModelable){
        return addGroupModelable(groupModelable,new ArrayList<Modelable>());
    }

    /**
     * add a new group model to the group model list and
     * add a new group key with the given child model list
     * to the child model map
     * @param groupModelable The group modelable to add
     * @param childModelableList The child modelable list
     * @return True if group modelable was added
     */
    public boolean addGroupModelable(Modelable groupModelable, ArrayList<Modelable> childModelableList){
        if(!groupModelableList.contains(groupModelable)){
            if(groupModelableList.add(groupModelable)){
                childModelableMap.put(groupModelable,childModelableList);
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
            ArrayList<Modelable> childModelableList = getChildList(groupPosition);
            if(!childModelableList.contains(childModelable)){
                return childModelableList.add(childModelable);
            }
        }return false;
    }

    /**
     *
     * @param groupPosition the position of the group modelable in the list
     * @return True if group modelable was removed
     */
    public boolean removeGroupModelable(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            Modelable groupModelable = groupModelableList.get(groupPosition);
            childModelableMap.remove(groupModelable);
            return groupModelableList.remove(groupModelable);
        }return false;
    }

    /**
     *
     * @param groupPosition The position of the group modelable key
     * @param childPosition The position of the child modelable to remove
     * @return True if the child modelable was removed
     */
    public boolean removeChildModelable(int groupPosition, int childPosition){
        if(isValidGroupPosition(groupPosition)){
            ArrayList<Modelable> childModelableList = getChildList(groupPosition);
            if(isValidChildPosition(childPosition,childModelableList)){
                return childModelableList.remove(childModelableList.get(childPosition));
            }
        }return false;
    }

    /**
     * Cleans all the group modelable list
     * Therefore, clean all the child modelable map
     */
    public void clearGroupModelableList(){
        groupModelableList.clear();
        childModelableMap.clear();
    }

    /**
     * Cleans all the child modelable list associated with the modelable group
     * @param groupPosition index of the group modelable key
     */
    public void clearChildModelables(int groupPosition){
        if(isValidGroupPosition(groupPosition)){
            getChildList(groupPosition).clear();
        }
    }

    public void restoreModelableInstance(Bundle instance){
        if(instance != null){
            groupModelableList = instance.getParcelableArrayList(MODELABLE_LIST);
            if(groupModelableList != null){
                for(Modelable groupModelable : groupModelableList){
                    String tagId = MODELABLE_LIST + groupModelable.getItemId();
                    ArrayList<Modelable> childModelableList = instance.getParcelableArrayList(tagId);
                    if(childModelableList != null){
                        childModelableMap.put(groupModelable,childModelableList);
                    }
                }
            }
        }
    }

    public void saveModelableInstance(Bundle instance){
        if(getGroupCount() > 0){
            instance.putParcelableArrayList(MODELABLE_LIST,groupModelableList);
            for(Modelable groupModelable : groupModelableList){
                ArrayList<Modelable> childModelableList = childModelableMap.get(groupModelable);
                if(childModelableList != null && childModelableList.size() > 0){
                    String tagId = MODELABLE_LIST + groupModelable.getItemId();
                    instance.putParcelableArrayList(tagId, childModelableList);
                }
            }
        }
    }

    // +++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++

    @Override
    public int getGroupCount(){
        return groupModelableList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return getChildList(groupPosition).size();
    }

    @Override
    public Modelable getGroup(int groupPosition){
        return groupModelableList.get(groupPosition);
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        return getChildList(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return getGroup(groupPosition).getItemId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return getChild(groupPosition,childPosition).getItemId();
    }

    @Override
    public boolean hasStableIds(){
        return stableIds;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent){
        Modelable groupModelable = getGroup(groupPosition);
        groupModelable.setGroupPosition(groupPosition);
        Expandable groupItem;
        if(view != null){
            groupItem = (Expandable)view.getTag();
        }else {
            int viewType = groupModelable.getViewType();
            groupItem = groupListener.getExpandableItem(viewType,false);
            view = groupItem.getExpandableView(parent);
        }
        groupItem.setExpanded(isExpanded);
        groupItem.onBindHolder(groupModelable);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent){
        Modelable childModelable = getChild(groupPosition,childPosition);
        childModelable.setGroupPosition(groupPosition);
        childModelable.setChildPosition(childPosition);
        Expandable childItem;
        if(view != null){
            childItem = (Expandable)view.getTag();
        }else {
            int viewType = childModelable.getViewType();
            childItem = childListener.getExpandableItem(viewType,true);
            view = childItem.getExpandableView(parent);
        }
        childItem.setLastChild(isLastChild);
        childItem.onBindHolder(childModelable);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return getChild(groupPosition,childPosition).isEnabled();
    }

    @Override
    public int getGroupType(int groupPosition){
        return getGroup(groupPosition).getViewType();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition){
        return getChild(groupPosition,childPosition).getViewType();
    }

    @Override
    public int getGroupTypeCount(){
        return groupTypeCount;
    }

    @Override
    public int getChildTypeCount(){
        return childTypeCount;
    }

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private ArrayList<Modelable> getChildList(int groupPosition){
        return getChildList(getGroup(groupPosition));
    }

    private ArrayList<Modelable> getChildList(Modelable groupModelable){
        return childModelableMap.get(groupModelable);
    }

    private boolean isValidGroupPosition(int groupPosition){
        return groupPosition > NO_ITEMS && groupPosition < groupModelableList.size();
    }

    private boolean isValidChildPosition(int childPosition, ArrayList<Modelable> childModelableList){
        return childPosition > NO_ITEMS && childPosition < childModelableList.size();
    }
}