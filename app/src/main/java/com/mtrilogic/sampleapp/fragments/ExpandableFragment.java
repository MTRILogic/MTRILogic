package com.mtrilogic.sampleapp.fragments;

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
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.items.expandables.childs.ChildDataItem;
import com.mtrilogic.sampleapp.items.expandables.childs.ChildImageItem;
import com.mtrilogic.sampleapp.items.expandables.groups.GroupDataItem;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.sampleapp.pages.ExpandablePage;
import com.mtrilogic.sampleapp.types.GroupType;
import com.mtrilogic.sampleapp.types.ChildType;

@SuppressWarnings("unused")
public class ExpandableFragment extends Fragmentable implements ExpandableListener, ExpandableAdapterListener{
    private static final String TAG = "ExpandableFragment";
    private static final String PAGE = "page";
    private FragmentableAdapterListener listener;
    private ExpandableAdapter adapter;
    private ExpandablePage page;
    private int position;

// ++++++++++++++++| PUBLIC STATIC METHODS |+++++++++++++++++++++++++++++++++++

    public static ExpandableFragment getInstance(ExpandablePage page){
        Bundle args = new Bundle();
        args.putParcelable(PAGE, page);
        ExpandableFragment fragment = new ExpandableFragment();
        fragment.setArguments(args);
        return fragment;
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentableAdapterListener){
            listener = (FragmentableAdapterListener)context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            page = args.getParcelable(PAGE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        position = listener.getFragmentableAdapter().getPaginablePosition(page);
        adapter = new ExpandableAdapter(this, page.getGroupModelableList(), page.getChildModelableMap(), GroupType.COUNT, ChildType.COUNT);
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        lvwItems.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id){
                if(parent.isGroupExpanded(groupPosition)){
                    parent.collapseGroup(groupPosition);
                }else{
                    parent.expandGroup(groupPosition,false);
                    parent.setSelection(groupPosition);
                }
                return true;
            }
        });
        TextView lblTitle = view.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        TextView lblContent = view.findViewById(R.id.lbl_content);
        lblContent.setText(getString(R.string.content_item, position));
        ImageButton btnAddGroup = view.findViewById(R.id.btn_addGroup);
        btnAddGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addGroup();
            }
        });
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                delete();
            }
        });
        return view;
    }

    @Override
    public void onDetach(){
        listener = null;
        super.onDetach();
    }

    @Override
    public Paginable getPaginable(){
        return page;
    }

    @Override
    public int getPosition(){
        return position;
    }

    @Override
    public ExpandableGroup getExpandableGroup(int viewType){
        return new GroupDataItem(getContext(),this, R.layout.item_group);
    }

    @Override
    public ExpandableChild getExpandableChild(int viewType){
        Context context = getContext();
        switch(viewType){
            case ChildType.DATA:
                return new ChildDataItem(context, this, R.layout.item_child_data);
            case ChildType.IMAGE:
                return new ChildImageItem(context, this, R.layout.item_child_image);
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

    private void addGroup(){
        long idx = page.getGroupModelableList().idx;
        DataModel model = new DataModel(idx, GroupType.GROUP);
        if(adapter.addGroupModelable(model)){
            adapter.notifyDataSetChanged();
            page.getGroupModelableList().idx++;
        }
    }

    private void delete(){
        FragmentableAdapter adapter = listener.getFragmentableAdapter();
        if(adapter.removePaginable(page)){
            adapter.notifyDataSetChanged();
        }
    }
}
