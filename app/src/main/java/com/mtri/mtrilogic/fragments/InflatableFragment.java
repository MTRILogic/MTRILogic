package com.mtri.mtrilogic.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mtri.abstracts.Fragmentable;
import com.mtri.abstracts.Inflatable;
import com.mtri.adapters.InflatableAdapter;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.items.inflatables.DataItem;
import com.mtri.mtrilogic.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class InflatableFragment extends Fragmentable{
    private List<Inflatable> items;
    private InflatableAdapter adapter;
    private ListView lvwItems;
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
        items = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            DataModel model = new DataModel();
            model.setTitle(getString(R.string.title_count,i));
            model.setContent(getString(R.string.content));
            model.setIcon(R.mipmap.ic_launcher_round);
            if(items.add(new DataItem(idx, model))){
                idx++;
            }
        }
        adapter = new InflatableAdapter<>(items,getContext());
        adapter.notifyDataSetChanged();
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        return view;
    }
}
