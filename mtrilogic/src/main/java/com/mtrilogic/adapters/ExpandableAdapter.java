package com.mtrilogic.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtrilogic.abstracts.Expandable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableAdapter extends BaseExpandableListAdapter{
   // private static final String TAG = "ExpandableAdapterTAG";
    private ExpandableListener childListener, groupListener;
    private List<Modelable> groupModelList;
    private Map<Modelable,List<Modelable>> childModelMap;
    private int groupTypeCount, childTypeCount;
    private boolean stableIds;

    @SuppressWarnings("unused")
    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener){
        this(groupListener,childListener,1,1);
    }

    @SuppressWarnings("WeakerAccess")
    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, int groupTypeCount, int childTypeCount){
        this(groupListener,childListener,new ArrayList<Modelable>(),new HashMap<Modelable, List<Modelable>>(),groupTypeCount,childTypeCount);
    }

    @SuppressWarnings("unused")
    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, List<Modelable> groupModelList, Map<Modelable,List<Modelable>> childModelMap){
        this(groupListener,childListener,groupModelList,childModelMap,1,1);
    }

    @SuppressWarnings("WeakerAccess")
    public ExpandableAdapter(ExpandableListener groupListener, ExpandableListener childListener, List<Modelable> groupModelList, Map<Modelable,List<Modelable>> childModelMap, int groupTypeCount, int childTypeCount){
        this.groupListener = groupListener;
        this.childListener = childListener;
        this.groupModelList = groupModelList;
        this.childModelMap = childModelMap;
        groupTypeCount = groupTypeCount > 0 ? groupTypeCount : 1;
        this.groupTypeCount = groupTypeCount;
        childTypeCount = childTypeCount > 0 ? childTypeCount : 1;
        this.childTypeCount = childTypeCount;
        stableIds = true;
    }

    //replace a group model in a given position and
    //returns the replaced group model
    @SuppressWarnings("unused")
    public Modelable setGroupModel(int groupPosition, Modelable groupModel){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            return groupModelList.set(groupPosition, groupModel);
        }
        return null;
    }

    //replace a child model in a given position and
    //returns the replaced child model
    @SuppressWarnings("unused")
    public Modelable setChildModel(int groupPosition, int childPosition, Modelable childModel){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            Modelable groupModel = getGroup(groupPosition);
            List<Modelable> childModels = getChildModels(groupModel);
            if(childPosition > -1 && childPosition < childModels.size()){
                return childModels.set(childPosition,childModel);
            }
        }
        return null;
    }

    //replaces the current group model list and
    //recreate a new child model map from it
    @SuppressWarnings("unused")
    public void setGroupModels(List<Modelable> groupModelList){
        this.groupModelList = groupModelList;
        childModelMap.clear();
        for(Modelable groupModel : groupModelList){
            childModelMap.put(groupModel,new ArrayList<Modelable>());
        }
    }

    //returns the current group model list
    @SuppressWarnings("unused")
    public List<Modelable> getGroupModels(){
        return groupModelList;
    }

    //replaces the current child model map for a new child model map
    //and a new group list is created from the key-set of the child model map
    //returns true if this last operation was successful
    @SuppressWarnings("unused")
    public boolean setChildModelMap(Map<Modelable,List<Modelable>> childModelMap){
        this.childModelMap = childModelMap;
        groupModelList.clear();
        return groupModelList.addAll(childModelMap.keySet());
    }

    //returns the complete child model map
    @SuppressWarnings("unused")
    public Map<Modelable,List<Modelable>> getChildModelMap(){
        return childModelMap;
    }

    //replaces the child model list associated with a given group position
    @SuppressWarnings("unused")
    public void setChildModelList(int groupPosition, List<Modelable> childModelList){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            childModelMap.put(getGroup(groupPosition),childModelList);
        }
    }

    //returns the child model list associated to the given group position
    @SuppressWarnings("unused")
    public List<Modelable> getChildModelList(int groupPosition){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            return getChildModels(groupPosition);
        }
        return null;
    }

    // add a new group model to the group model list and
    // add a new group key with an empty child model list value
    // to the child model map
    @SuppressWarnings("unused")
    public void addGroupModel(Modelable groupModel){
        if(!groupModelList.contains(groupModel)){
            if(groupModelList.add(groupModel)){
                childModelMap.put(groupModel,new ArrayList<Modelable>());
            }
        }
    }

    // add a new group model to the group model list and
    // add a new group key with the given child model list
    // to the child model map
    @SuppressWarnings("unused")
    public void addGroupModel(Modelable groupModel, List<Modelable> childModelList){
        if(!groupModelList.contains(groupModel)){
            if(groupModelList.add(groupModel)){
                childModelMap.put(groupModel,childModelList);
            }
        }
    }

    // add a new child model to the child model list
    // associated with the given group position
    @SuppressWarnings("UnusedReturnValue")
    public boolean addChildModel(int groupPosition, Modelable childModel){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            Modelable groupModel = getGroup(groupPosition);
            if(childModelMap.containsKey(groupModel)){
                List<Modelable> childModels = getChildModels(groupModel);
                if(!childModels.contains(childModel)){
                    return childModels.add(childModel);
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    public boolean removeGroupModel(int groupPosition){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            return groupModelList.remove(groupModelList.get(groupPosition));
        }
        return false;
    }

    @SuppressWarnings("unused")
    public boolean removeChildModel(int groupPosition, int childPosition){
        if(groupPosition > -1 && groupPosition < groupModelList.size()){
            Modelable groupModel = getGroup(groupPosition);
            if(childModelMap.containsKey(groupModel)){
                List<Modelable> childModels = getChildModels(groupModel);
                if(childPosition > -1 && childPosition < childModels.size()){
                    return childModels.remove(childModels.get(childPosition));
                }
            }
        }
        return false;
    }

    //clear all group and child models containers
    @SuppressWarnings("unused")
    public void clearModels(){
        groupModelList.clear();
        childModelMap.clear();
    }

    @SuppressWarnings("unused")
    public void setStableIds(boolean stableIds){
        this.stableIds = stableIds;
    }

    @Override
    public int getGroupCount(){
        return groupModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return getChildModels(groupPosition).size();
    }

    @Override
    public Modelable getGroup(int groupPosition){
        return groupModelList.get(groupPosition);
    }

    @Override
    public Modelable getChild(int groupPosition, int childPosition){
        return getChildModels(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return getGroup(groupPosition).getItemId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return getChildModels(groupPosition).get(childPosition).getItemId();
    }

    @Override
    public boolean hasStableIds(){
        return stableIds;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        Modelable groupModel = getGroup(groupPosition);
        groupModel.setGroupPosition(groupPosition);
        Expandable groupItem;
        if(convertView != null){
            groupItem = (Expandable)convertView.getTag();
        }else {
            int viewType = groupModel.getViewType();
            groupItem = groupListener.getExpandableItem(viewType,false);
            convertView = groupItem.getExpandableView(parent);
        }
        groupItem.setExpanded(isExpanded);
        groupItem.onBindHolder(groupModel);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        Modelable childModel = getChild(groupPosition,childPosition);
        childModel.setGroupPosition(groupPosition);
        childModel.setChildPosition(childPosition);
        Expandable childItem;
        if(convertView != null){
            childItem = (Expandable)convertView.getTag();
        }else {
            int viewType = childModel.getViewType();
            childItem = childListener.getExpandableItem(viewType,true);
            convertView = childItem.getExpandableView(parent);
        }
        childItem.setLastChild(isLastChild);
        childItem.onBindHolder(childModel);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return getChildModels(groupPosition).get(childPosition).isEnabled();
    }

    @Override
    public int getGroupType(int groupPosition){
        return getGroup(groupPosition).getViewType();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition){
        return getChildModels(groupPosition).get(childPosition).getViewType();
    }

    @Override
    public int getGroupTypeCount(){
        return groupTypeCount;
    }

    @Override
    public int getChildTypeCount(){
        return childTypeCount;
    }

    private List<Modelable> getChildModels(int groupPosition){
        return getChildModels(getGroup(groupPosition));
    }

    private List<Modelable> getChildModels(Modelable groupModel){
        return childModelMap.get(groupModel);
    }
}