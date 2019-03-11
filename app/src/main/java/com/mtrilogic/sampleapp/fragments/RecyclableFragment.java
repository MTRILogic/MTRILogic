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
import com.mtrilogic.abstracts.Paginable;
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

@SuppressWarnings("unused")
public class RecyclableFragment extends Fragmentable implements RecyclableListener, RecyclableAdapterListener{
    private static final String TAG = "RecyclableFragmentTAG";
    private OnMakeToastListener listener;
    private RecyclableAdapter adapter;
    private Paginable paginable;

    // +++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++

    public static RecyclableFragment getInstance(Paginable paginable){
        RecyclableFragment fragment = new RecyclableFragment();
        fragment.paginable = paginable;
        return fragment;
    }

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public RecyclableFragment(){}

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

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
        setRetainInstance(true);
        loadModelList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_recyclable,container,false);
        RecyclerView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setLayoutManager(new LinearLayoutManager(context));
        lvwItems.setAdapter(adapter);
        return view;
    }

    @Override
    public Paginable getPaginable(){
        return paginable;
    }

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

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private void loadModelList(){
        adapter = new RecyclableAdapter(this);
        Context context = getContext();
        String[] links; int n = 0;
        if(context != null){
            links = context.getResources().getStringArray(R.array.links);
        }else {
            links = new String[5];
        }
        int count = 0;
        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                DataModel model = new DataModel(true);
                model.setTitle(getString(R.string.title_count,i));
                model.setContent(getString(R.string.content));
                model.setIcon(R.mipmap.ic_launcher_round);
                if(adapter.addModelable(model)){
                    count++;
                }
            }else {
                ImageModel model = new ImageModel(true);
                model.setImageLink(links[n++]);
                model.setRating((int)(Math.random() * 5) + 1);
                if(adapter.addModelable(model)){
                    count++;
                }
            }
        }
        if(count > 0){
            adapter.notifyDataSetChanged();
        }
    }
}
