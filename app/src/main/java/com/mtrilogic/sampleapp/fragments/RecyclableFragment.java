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
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.items.recyclables.RecyclableDataItem;
import com.mtrilogic.sampleapp.items.recyclables.RecyclableImageItem;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.sampleapp.viewtypes.DataViewType;

import java.util.ArrayList;
import java.util.List;

public class RecyclableFragment extends Fragmentable implements RecyclableListener, RecyclableAdapterListener{
    //private static final String TAG = "RecyclableFragmentTAG";
    private OnMakeToastListener listener;
    private List<Modelable> models;
    private RecyclableAdapter adapter;
    private String pageTitle;
    private long itemId, idx;

    public static RecyclableFragment getInstance(String pageTitle, long itemId){
        RecyclableFragment fragment = new RecyclableFragment();
        fragment.pageTitle = pageTitle;
        fragment.itemId = itemId;
        return fragment;
    }

    public RecyclableFragment(){
        models = new ArrayList<>();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadModelList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Context context = getContext();
        adapter = new RecyclableAdapter(this,models);
        View view = inflater.inflate(R.layout.fragment_recyclable,container,false);
        RecyclerView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setLayoutManager(new LinearLayoutManager(context));
        lvwItems.setAdapter(adapter);
        return view;
    }

    //Fragmentable
    @Override
    public String getPageTitle(){
        return pageTitle;
    }

    @Override
    public long getItemId(){
        return itemId;
    }

    //RecyclableListener
    @Override
    public Recyclable getRecyclableItem(int viewType){
        Context context = getContext();
        switch(viewType){
            case DataViewType.DATA:
                return new RecyclableDataItem(context,this);
            case DataViewType.IMAGE:
                return new RecyclableImageItem(context,this);
        }
        return null;
    }

    @Override
    public RecyclableAdapter getRecyclableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        listener.onMakeToast(line);
    }

    private void loadModelList(){
        Context context = getContext();
        String[] links; int n = 0;
        if(context != null){
            links = context.getResources().getStringArray(R.array.links);
        }else {
            links = new String[5];
        }
        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                DataModel model = new DataModel(idx);
                model.setTitle(getString(R.string.title_count,i));
                model.setContent(getString(R.string.content));
                model.setIcon(R.mipmap.ic_launcher_round);
                if(models.add(model)){
                    idx++;
                }
            }else {
                ImageModel model = new ImageModel(idx);
                model.setImageLink(links[n++]);
                model.setRating((int)(Math.random() * 5) + 1);
                if(models.add(model)){
                    idx++;
                }
            }
        }
    }
}
