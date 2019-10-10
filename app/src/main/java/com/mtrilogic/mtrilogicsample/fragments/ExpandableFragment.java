package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildDataItem;
import com.mtrilogic.mtrilogicsample.items.expandables.childs.ChildImageItem;
import com.mtrilogic.mtrilogicsample.items.expandables.groups.GroupDataItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.pages.ExpandablePage;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.types.GroupType;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings("unused")
public class ExpandableFragment extends Fragmentable<ExpandablePage> implements ExpandableListener,
        ExpandableAdapterListener{
    private static final String TAG = "ExpandableFragment";
    private ExpandableAdapter adapter;

// ++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static ExpandableFragment getInstance(Paginable paginable){
        Bundle args = new Bundle();
        args.putParcelable(PAGE, paginable);
        ExpandableFragment fragment = new ExpandableFragment();
        fragment.setArguments(args);
        return fragment;
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        ExpandablePage page = getPage();
        if (page != null) {
            Listable groupListable = page.getGroupListable();
            Mapable childMapable = page.getChildMapable();
            adapter = new ExpandableAdapter(this, groupListable, childMapable, GroupType.COUNT,
                    ChildType.COUNT);
            ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
            lvwItems.setAdapter(adapter);
            TextView lblTitle = view.findViewById(R.id.lbl_title);
            lblTitle.setText(getString(R.string.title_item, page.getItemId()));
            TextView lblContent = view.findViewById(R.id.lbl_content);
            lblContent.setText(getString(R.string.content_item, getPosition()));
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
        }
        return view;
    }

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

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        FragmentableAdapterListener listener = getListener();
        if (listener != null) {
            listener.onMakeToast(line);
        }
    }

// *************************************************************************************************

    private void addGroup(){
        Listable groupListable = getPage().getGroupListable();
        long idx = groupListable.getIdx();
        DataModel model = new DataModel(idx, GroupType.GROUP);
        if(adapter.appendGroupModelable(model, new Listable())){
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
