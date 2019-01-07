package com.mtri.mtrilogic.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.mtri.abstracts.Fragmentable;
import com.mtri.adapters.ExpandableAdapter;
import com.mtri.interfaces.OnAdapterChangedListener;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.items.expandables.childs.ChildItem;
import com.mtri.mtrilogic.items.expandables.groups.GroupItem;
import com.mtri.mtrilogic.models.ChildModel;
import com.mtri.mtrilogic.models.GroupModel;

public class ExpandableFragment extends Fragmentable implements OnAdapterChangedListener{
    private ExpandableAdapter adapter;
    private long idx;

    public static ExpandableFragment getInstance(String title, long id){
        ExpandableFragment fragment = new ExpandableFragment();
        fragment.setPageTitle(title);
        fragment.setItemId(id);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        adapter = new ExpandableAdapter(getContext());
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        if(savedInstanceState == null){
            idx = 0;
            for(int y = 0; y < 5; y++){
                GroupModel groupModel = new GroupModel();
                groupModel.setTitle(getString(R.string.title_count,y));
                adapter.addGroup("book" + y,new GroupItem(this,groupModel,idx++));
                for(int x = 0; x < 5; x++){
                    ChildModel childModel = new ChildModel();
                    childModel.setTitle(getString(R.string.title_count,x));
                    childModel.setContent(getString(R.string.content));
                    childModel.setIcon(R.mipmap.ic_launcher);
                    adapter.addChild("book" + y,new ChildItem(this,childModel,idx++,true));
                }
            }
            if(idx > 0){
                adapter.notifyDataSetChanged();
            }
        }
        return view;
    }

    @Override
    public void onNotifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }
}
