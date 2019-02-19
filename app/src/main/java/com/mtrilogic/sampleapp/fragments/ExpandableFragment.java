package com.mtrilogic.sampleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mtrilogic.abstracts.Expandable;
import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.items.expandables.childs.ChildItem;
import com.mtrilogic.sampleapp.items.expandables.groups.GroupItem;
import com.mtrilogic.sampleapp.models.ChildModel;
import com.mtrilogic.sampleapp.models.GroupModel;

public class ExpandableFragment extends Fragmentable implements ExpandableListener, ExpandableAdapterListener{
    //private static final String TAG = "ExpandableFragmentTAG";
    private OnMakeToastListener listener;
    private ExpandableAdapter adapter;
    private String pageTitle;
    private long itemId, idx;

    public static ExpandableFragment getInstance(String pageTitle, long itemId){
        ExpandableFragment fragment = new ExpandableFragment();
        fragment.pageTitle = pageTitle;
        fragment.itemId = itemId;
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnMakeToastListener){
            listener = (OnMakeToastListener)context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadExpandableList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        return view;
    }

    @Override
    public String getPageTitle(){
        return pageTitle;
    }

    @Override
    public long getItemId(){
        return itemId;
    }

    @Override
    public Expandable getExpandableItem(int viewType, boolean isChild){
        Context context = getContext();
        if(isChild){
            switch(viewType){
                case 0:
                    return new ChildItem(context,this);
            }
        }else {
            switch(viewType){
                case 0:
                    return new GroupItem(context,this);
            }
        }
        return null;
    }

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        listener.onMakeToast(line);
    }

    private void loadExpandableList(){
        adapter = new ExpandableAdapter(this,this);
        for(int y = 0; y < 5; y++){
            GroupModel groupModel = new GroupModel(idx++);
            groupModel.setTitle(getString(R.string.title_count,y));
            adapter.addGroupModel(groupModel);
            for(int x = 0; x < 5; x++){
                ChildModel childModel = new ChildModel(idx++);
                childModel.setTitle(getString(R.string.title_count,x));
                childModel.setContent(getString(R.string.content));
                childModel.setIcon(R.mipmap.ic_launcher);
                adapter.addChildModel(y,childModel);
            }
        }
    }
}
