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
import com.mtrilogic.abstracts.Paginable;
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

@SuppressWarnings("unused")
public class InflatableFragment extends Fragmentable implements AdapterView.OnItemClickListener, InflatableListener, InflatableAdapterListener{
    private static final String TAG = "InflatableFragmentTAG";
    private OnMakeToastListener listener;
    private InflatableAdapter adapter;
    private Paginable paginable;

    // +++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++

    public static InflatableFragment getInstance(Paginable paginable){
        InflatableFragment fragment = new InflatableFragment();
        fragment.paginable = paginable;
        return fragment;
    }

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public InflatableFragment(){}

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
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        ListView lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
        lvwItems.setOnItemClickListener(this);
        return view;
    }

    @Override
    public Paginable getPaginable(){
        return paginable;
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

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private void loadModelList(){
        adapter = new InflatableAdapter(this,DataViewType.COUNT);
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
