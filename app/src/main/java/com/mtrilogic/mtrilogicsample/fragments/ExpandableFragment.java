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
    public void onAttach(@NonNull Context context){
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
        Listable groupListable = page.getGroupListable();
        Mapable childMapable = page.getChildMapable();
        adapter = new ExpandableAdapter(this, groupListable, childMapable, GroupType.COUNT, ChildType.COUNT);
        View view = inflater.inflate(R.layout.fragment_expandable,container,false);
        ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        /*lvwItems.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
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
        });*/
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
        Listable groupListable = page.getGroupListable();
        long idx = groupListable.getIdx();
        DataModel model = new DataModel(idx, GroupType.GROUP);
        if(adapter.appendGroupModelable(model, new Listable())){
            adapter.notifyDataSetChanged();
            groupListable.setIdx(++idx);
        }
    }

    private void delete(){
        FragmentableAdapter adapter = listener.getFragmentableAdapter();
        if(adapter.removePaginable(page)){
            adapter.notifyDataSetChanged();
        }
    }
}
