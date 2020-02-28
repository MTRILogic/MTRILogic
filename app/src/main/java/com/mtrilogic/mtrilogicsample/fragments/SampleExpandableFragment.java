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
import com.mtrilogic.mtrilogicsample.databinding.FragmentExpandableBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildDataBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildImageBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemGroupBinding;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildDataItem;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildImageItem;
import com.mtrilogic.mtrilogicsample.items.expandables.groups.GroupDataItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.pages.SampleMapPaginable;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.ItemGroupType;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings("unused")
public class SampleExpandableFragment extends ExpandableFragment<SampleMapPaginable, FragmentExpandableBinding> {
    private TextView lblContent;

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onNewPosition(int position) {
        lblContent.setText(getString(R.string.content_item, position));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentExpandableBinding.inflate(inflater, container, false);
        ExpandableView lvwItems = binding.lvwItems;
        bindExpandable(lvwItems, ItemGroupType.COUNT, ItemChildType.COUNT);
        TextView lblTitle = binding.lblTitle;
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        lblContent = binding.lblContent;
        ImageButton btnAddGroup = binding.btnAddGroup;
        btnAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroupModelable();
            }
        });
        ImageButton btnDelete = binding.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        return binding.getRoot();
    }

    @Override
    public ExpandableGroup getExpandableGroup(int viewType, LayoutInflater inflater, ViewGroup parent){
        if (viewType == ItemGroupType.GROUP) {
            return new GroupDataItem(ItemGroupBinding.inflate(inflater, parent, false), this);
        }
        return null;
    }

    @Override
    public ExpandableChild getExpandableChild(int viewType, LayoutInflater inflater, ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ItemChildType.DATA:
                return new ChildDataItem(ItemChildDataBinding.inflate(inflater, parent, false), this);
            case ItemChildType.IMAGE:
                return new ChildImageItem(ItemChildImageBinding.inflate(inflater, parent, false), this);
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

    // ================< PRIVATE METHODS >==========================================================

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
