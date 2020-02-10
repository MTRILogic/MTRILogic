package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableFragment;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildDataItem;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildImageItem;
import com.mtrilogic.mtrilogicsample.items.expandables.groups.GroupDataItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.pages.SampleMapablePage;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.GroupType;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings("unused")
public class SampleExpandableFragment extends ExpandableFragment<SampleMapablePage> {
    private TextView lblContent;

// PROTECTED OVERRIDE METHODS |*********************************************************************

    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        initExpandable(view, GroupType.COUNT, ChildType.COUNT);
        TextView lblTitle = view.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnAddGroup = view.findViewById(R.id.btn_addGroup);
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroupModelable();
            }
        });
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        return view;
    }

    @Override
    protected void onNewPosition() {
        lblContent.setText(getString(R.string.content_item, position));
    }

    @Override
    protected void onExpandableCreated() {

    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public ExpandableGroup getExpandableGroup(int viewType, LayoutInflater inflater, ViewGroup parent){
        if (viewType == GroupType.GROUP) {
            return new GroupDataItem(inflater, R.layout.item_group, parent, this);
        }
        return null;
    }

    @Override
    public ExpandableChild getExpandableChild(int viewType, LayoutInflater inflater, ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ChildType.DATA:
                return new ChildDataItem(inflater, R.layout.item_child_data, parent, this);
            case ChildType.IMAGE:
                return new ChildImageItem(inflater, R.layout.item_child_image, parent, this);
        }
        return null;
    }

    @Override
    public boolean onItemLongClick(View view, Modelable modelable, int position) {
        return false;
    }

    @Override
    public void onItemClick(View view, Modelable modelable, int position) {

    }

// *************************************************************************************************

    private void addGroupModelable(){
        Listable groupListable = page.getGroupListable();
        long idx = groupListable.getIdx();
        DataModel model = new DataModel(idx, false);
        if(adapter.appendGroupModelable(model, new Listable<>())){
            adapter.notifyDataSetChanged();
            groupListable.setIdx(++idx);
        }
    }
}
