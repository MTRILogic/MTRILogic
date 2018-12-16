package com.mtri.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.mtri.abstracts.ExpandableChild;
import com.mtri.abstracts.ExpandableGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableAdapter extends BaseExpandableListAdapter{
    private Map<String,ExpandableGroup> groupMap;
    private List<String> keys;
    private Context context;

    public ExpandableAdapter(Context context){
        this.context = context;
        groupMap = new HashMap<>();
        keys = new ArrayList<>();
    }

    public void addGroup(String key, ExpandableGroup group){
        if(!groupMap.containsKey(key)){
            groupMap.put(key,group);
            keys.add(key);
        }
    }

    public void addChild(String key, ExpandableChild child){
        if(groupMap.containsKey(key)){
            getExpandableGroup(key).getChildList().add(child);
        }
    }

    @Override
    public int getGroupCount(){
        return groupMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return getGroup(groupPosition).getChildList().size();
    }

    @Override
    public ExpandableGroup getGroup(int groupPosition){
        return getExpandableGroup(keys.get(groupPosition));
    }

    @Override
    public ExpandableChild getChild(int groupPosition, int childPosition){
        return getGroup(groupPosition).getChildList().get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        return getGroup(groupPosition).getExpandableView(isExpanded,convertView,parent,context);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        return getChild(groupPosition,childPosition).getExpandableView(isLastChild,convertView,parent,context);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return getChild(groupPosition,childPosition).isSelectable();
    }

    private ExpandableGroup getExpandableGroup(String key){
        return groupMap.get(key);
    }
}