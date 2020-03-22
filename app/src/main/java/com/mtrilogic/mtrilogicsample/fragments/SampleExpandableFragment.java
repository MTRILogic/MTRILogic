package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.BindingExpandableChild;
import com.mtrilogic.abstracts.BindingExpandableFragment;
import com.mtrilogic.abstracts.BindingExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.mtrilogicsample.databinding.FragmentExpandableBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildDataBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildImageBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemGroupBinding;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildDataItemBinding;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildImageItemBinding;
import com.mtrilogic.mtrilogicsample.items.expandables.groups.GroupDataItemBinding;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.pages.SampleMapPaginable;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.ItemGroupType;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings("unused")
public class SampleExpandableFragment extends BindingExpandableFragment<
        SampleMapPaginable, FragmentListener, FragmentExpandableBinding> {
    private TextView lblContent;

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected FragmentListener getListenerFromContext(@NonNull Context context) {
        return (FragmentListener) context;
    }

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

        Context context = getContext();
        if (context != null){
            bindExpandable(context, lvwItems, ItemGroupType.COUNT, ItemChildType.COUNT);
        }

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
    public BindingExpandableGroup getExpandableGroup(int viewType, @NonNull LayoutInflater inflater,
                                                     @NonNull ViewGroup parent){
        if (viewType == ItemGroupType.GROUP) {
            return new GroupDataItemBinding(ItemGroupBinding.inflate(inflater, parent, false), this);
        }
        return null;
    }

    @Override
    public BindingExpandableChild getExpandableChild(int viewType, @NonNull LayoutInflater inflater,
                                                     @NonNull ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ItemChildType.DATA:
                return new ChildDataItemBinding(ItemChildDataBinding.inflate(inflater, parent, false), this);
            case ItemChildType.IMAGE:
                return new ChildImageItemBinding(ItemChildImageBinding.inflate(inflater, parent, false), this);
        }
        return null;
    }

    @Override
    public boolean onItemTouch(@NonNull View view, @NonNull MotionEvent event, @NonNull Modelable modelable, int position) {
        return false;
    }

    @Override
    public boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position) {
        return false;
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position) {

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
