package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.mtrilogic.mtrilogicsample.pages.SampleMapPage;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.ItemGroupType;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;
import com.mtrilogic.views.ExpandableView;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class SampleExpandableFragment extends BindingExpandableFragment<SampleMapPage, FragmentListener, FragmentExpandableBinding> {
    private TextView lblContent;
    private CheckBox chkItem;

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected FragmentListener getListenerFromContext(@NonNull Context context) {
        return (FragmentListener) context;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentExpandableBinding.inflate(inflater, container, false);
        ExpandableView lvwItems = binding.lvwItems;
        final TextView lblTitle = binding.lblTitle;

        Context context = getContext();
        if (context != null && page != null){
            bindExpandable(context, lvwItems, page, ItemGroupType.COUNT, ItemChildType.COUNT);
            lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        }

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
        chkItem = binding.chkItem;
        chkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (adapter != null && page != null){
                    Listable<Modelable> listable = adapter.getGroupListable();
                    if (listable != null){
                        ArrayList<Modelable> list = listable.getList();
                        if (list != null){
                            for (Modelable item : list){
                                DataModel model = (DataModel) item;
                                model.setChecked(isChecked);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onNewPosition(int position) {
        lblContent.setText(getString(R.string.content_item, position));
    }

    @Override
    public BindingExpandableGroup<?, ?, ?> getExpandableGroup(int viewType, @NonNull LayoutInflater inflater,
                                                     @NonNull ViewGroup parent){
        if (viewType == ItemGroupType.GROUP) {
            return new GroupDataItemBinding(ItemGroupBinding.inflate(inflater, parent, false),
                    this);
        }
        return null;
    }

    @Override
    public BindingExpandableChild<?, ?, ?> getExpandableChild(int viewType, @NonNull LayoutInflater inflater,
                                                     @NonNull ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ItemChildType.DATA:
                return new ChildDataItemBinding(ItemChildDataBinding.inflate(inflater, parent,
                        false), this);
            case ItemChildType.IMAGE:
                return new ChildImageItemBinding(ItemChildImageBinding.inflate(inflater, parent,
                        false), this);
        }
        return null;
    }

    // ================< PRIVATE METHODS >==========================================================

    private void addGroupModelable(){
        Listable<Modelable> groupListable = page.getGroupListable();
        DataModel model = new DataModel();
        model.setViewType(ItemChildType.DATA);
        if(adapter.appendGroupModelable(model, new Listable<>())){
            model.setChecked(chkItem.isChecked());
            adapter.notifyDataSetChanged();
        }
    }
}
