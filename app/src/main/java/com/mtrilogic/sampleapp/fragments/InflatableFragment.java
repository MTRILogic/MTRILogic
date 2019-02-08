package com.mtrilogic.sampleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;
import com.mtrilogic.interfaces.ViewTypeListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.items.inflatables.InflatableItem;
import com.mtrilogic.sampleapp.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class InflatableFragment extends Fragmentable implements OnNotifyDataSetChangedListener, ViewTypeListener{
    private List<Inflatable> items;
    private InflatableAdapter adapter;
    private long idx;

    public static InflatableFragment getInstance(String title, long id){
        InflatableFragment fragment = new InflatableFragment();
        fragment.setPageTitle(title);
        fragment.setItemId(id);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Context context = getContext();
        items = new ArrayList<>();
        adapter = new InflatableAdapter(items,context,this);
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        ListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        if(savedInstanceState == null){
            idx = 0;
            for(int i = 0; i < 10; i++){
                DataModel model = new DataModel();
                model.setTitle(getString(R.string.title_count,i));
                model.setContent(getString(R.string.content));
                model.setIcon(R.mipmap.ic_launcher_round);
                if(items.add(new InflatableItem(this,idx,model))){
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
    public int getItemViewType(int position){
        return 0;
    }

    @Override
    public int getViewTypeCount(){
        return 1;
    }
}
