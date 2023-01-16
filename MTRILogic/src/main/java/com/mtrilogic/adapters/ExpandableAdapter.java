package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.classes.Mappable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;

@SuppressWarnings("unused")
public final class ExpandableAdapter extends BaseExpandableListAdapter {
    private final ExpandableAdapterListener listener;
    private final LayoutInflater inflater;
    private final int groupTypeCount, childTypeCount;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableAdapter(@NonNull LayoutInflater inflater, int groupTypeCount, int childTypeCount, @NonNull ExpandableAdapterListener listener){
        this.groupTypeCount = Math.max(groupTypeCount, 1);
        this.childTypeCount = Math.max(childTypeCount, 1);
        this.inflater = inflater;
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public int getGroupCount() {
        return getModelMappable().getGroupCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getModelMappable().getChildCount(getGroup(groupPosition));
    }

    @Override
    public Model getGroup(int groupPosition) {
        return getModelMappable().getGroup(groupPosition);
    }

    @Override
    public Model getChild(int groupPosition, int childPosition) {
        return getModelMappable().getChild(getGroup(groupPosition), childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).getItemId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).getItemId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View itemView, ViewGroup parent) {
        Model group = getGroup(groupPosition);
        ExpandableGroup<? extends Model> groupItem;
        if (itemView != null){
            groupItem = (ExpandableGroup<?>) itemView.getTag();
        }else {
            groupItem = listener.getExpandableGroup(group.getViewType(), inflater, parent);
            itemView = groupItem.getItemView();
            itemView.setTag(groupItem);
        }
        groupItem.bindModel(group, groupPosition, isExpanded);
        return itemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View itemView, ViewGroup parent) {
        Model child = getChild(groupPosition, childPosition);
        ExpandableChild<? extends Model> childItem;
        if (itemView != null){
            childItem = (ExpandableChild<?>) itemView.getTag();
        }else {
            childItem = listener.getExpandableChild(child.getViewType(), inflater, parent);
            itemView = childItem.getItemView();
            itemView.setTag(childItem);
        }
        childItem.bindModel(child, groupPosition, childPosition, isLastChild);
        return itemView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).isEnabled();
    }

    @Override
    public int getGroupType(int groupPosition) {
        return getGroup(groupPosition).getViewType();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).getViewType();
    }

    @Override
    public int getGroupTypeCount() {
        return groupTypeCount;
    }

    @Override
    public int getChildTypeCount() {
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

    /*==============================================================================================
    PRIVATE METHOD
    ==============================================================================================*/

    private Mappable<Model> getModelMappable(){
        return listener.getModelMappable();
    }
}
