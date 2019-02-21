package com.mtrilogic.sampleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.items.inflatables.InflatableImageItem;
import com.mtrilogic.sampleapp.items.inflatables.InflatableDataItem;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.sampleapp.viewtypes.DataViewType;

import java.util.ArrayList;
import java.util.List;

public class InflatableFragment extends Fragmentable implements AdapterView.OnItemClickListener, InflatableListener, InflatableAdapterListener{
    //private static final String TAG = "InflatableFragmentTAG";
    private OnMakeToastListener listener;
    private List<Modelable> models;
    private InflatableAdapter adapter;
    private String pageTitle;
    private long itemId, idx;

    public static InflatableFragment getInstance(String pageTitle, long itemId){
        InflatableFragment fragment = new InflatableFragment();
        fragment.pageTitle = pageTitle;
        fragment.itemId = itemId;
        return fragment;
    }

    public InflatableFragment(){
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
        adapter = new InflatableAdapter(this,models,DataViewType.COUNT);
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        ListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        lvwItems.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        listener.onMakeToast("Item [" + position + "] clicked");
    }

    @Override
    public Inflatable getInflatableItem(int viewType){
        Context context = getContext();
        switch(viewType){
            case DataViewType.DATA:
                return new InflatableDataItem(context,this);
            case DataViewType.IMAGE:
                return new InflatableImageItem(context,this);
            default:
                return null;
        }
    }

    @Override
    public InflatableAdapter getInflatableAdapter(){
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
