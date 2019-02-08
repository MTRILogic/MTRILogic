package com.mtrilogic.sampleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.OnClickPositionListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.interfaces.OnNewHolderListener;
import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.holders.RecyclableHolder;
import com.mtrilogic.sampleapp.items.recyclables.RecyclableItem;
import com.mtrilogic.sampleapp.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclableFragment extends Fragmentable implements OnNotifyDataSetChangedListener, OnNewHolderListener, OnClickPositionListener{
    private OnMakeToastListener listener;
    private List<Recyclable> items;
    private RecyclableAdapter adapter;
    private long idx;

    public static RecyclableFragment getInstance(String title, long id){
        RecyclableFragment fragment = new RecyclableFragment();
        fragment.setPageTitle(title);
        fragment.setItemId(id);
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
    public void onDetach(){
        listener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Context context = getContext();
        items = new ArrayList<>();
        adapter = new RecyclableAdapter(this,items,context);
        View view = inflater.inflate(R.layout.fragment_recyclable,container,false);
        RecyclerView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setLayoutManager(new LinearLayoutManager(context));
        lvwItems.setAdapter(adapter);
        if(savedInstanceState == null){
            idx = 0;
            for(int i = 0; i < 10; i++){
                DataModel model = new DataModel();
                model.setTitle(getString(R.string.title_count,i));
                model.setContent(getString(R.string.content));
                model.setIcon(R.mipmap.ic_launcher_round);
                RecyclableItem item = new RecyclableItem(this,idx,model);
                if(items.add(item)){
                    idx++;
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

    @Override
    public RecyclerView.ViewHolder onNewHolder(ViewGroup parent, int viewType){
        switch(viewType){
            case 0:
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_data,parent,false);
                return new RecyclableHolder(view,this);
        }
        return null;
    }

    @Override
    public void onClickPosition(View view, int position){
        listener.onMakeToast("Item " + position);
    }
}
