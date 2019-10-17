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
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.abstracts.fragments.ExpandableFragment;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildDataItem;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildImageItem;
import com.mtrilogic.mtrilogicsample.items.expandables.groups.GroupDataItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.pages.SampleExpandablePage;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.GroupType;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings("unused")
public class SampleExpandableFragment extends ExpandableFragment<SampleExpandablePage> {
    private TextView lblContent;

// PROTECTED OVERRIDE METHODS |*********************************************************************

    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState,
                                        SampleExpandablePage page, int position) {
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        init(view, GroupType.COUNT, ChildType.COUNT, page);
        TextView lblTitle = view.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        lblContent = view.findViewById(R.id.lbl_content);
        onNewPosition(position);
        ImageButton btnAddGroup = view.findViewById(R.id.btn_addGroup);
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        return view;
    }

    @Override
    protected void onNewPosition(int position) {
        lblContent.setText(getString(R.string.content_item, position));
    }

    // ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public ExpandableGroup getExpandableGroup(int viewType, ViewGroup parent){
        if (viewType == GroupType.GROUP) {
            return new GroupDataItem(getContext(), R.layout.item_group, parent, this);
        }
        return null;
    }

    @Override
    public ExpandableChild getExpandableChild(int viewType, ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ChildType.DATA:
                return new ChildDataItem(context, R.layout.item_child_data, parent, this);
            case ChildType.IMAGE:
                return new ChildImageItem(context, R.layout.item_child_image, parent, this);
        }
        return null;
    }

// *************************************************************************************************

    private void addGroup(){
        Listable groupListable = getPage().getGroupListable();
        long idx = groupListable.getIdx();
        DataModel model = new DataModel(idx, GroupType.GROUP);
        ExpandableAdapter adapter = getExpandableAdapter();
        if(adapter.appendGroupModelable(model, new Listable<>())){
            adapter.notifyDataSetChanged();
            groupListable.setIdx(++idx);
        }
    }

    private void delete(){
        FragmentableAdapter adapter = getListener().getFragmentableAdapter();
        if(adapter.removePaginable(getPage())){
            adapter.notifyDataSetChanged();
        }
    }
}
