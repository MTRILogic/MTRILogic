package com.mtrilogic.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclableAdapter extends RecyclerView.Adapter<Recyclable>{
    //private static final String TAG = "RecyclableAdapterTAG";
    private RecyclableListener listener;
    private List<Modelable> models;

    @SuppressWarnings("unused")
    public RecyclableAdapter(RecyclableListener listener){
        this(listener,new ArrayList<Modelable>());
    }

    public RecyclableAdapter(RecyclableListener listener, List<Modelable> models){
        this.listener = listener;
        this.models = models;
        setHasStableIds(true);
    }

    public void setModels(List<Modelable> models){
        this.models = models;
    }

    public List<Modelable> getModels(){
        return models;
    }

    @SuppressWarnings("unused")
    public boolean addModel(Modelable model){
        if(!models.contains(model)){
            return models.add(model);
        }
        return false;
    }

    @SuppressWarnings("WeakerAccess")
    public Modelable getModel(int position){
        return models.get(position);
    }

    @SuppressWarnings("unused")
    public boolean removeModel(int position){
        if(position > -1 && position < models.size()){
            return models.remove(models.get(position));
        }
        return false;
    }

    @SuppressWarnings("unused")
    public void clearModels(){
        models.clear();
    }

    @NonNull
    @Override
    public Recyclable onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return listener.getRecyclableItem(viewType).getRecyclableHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable holder, int position){
        Modelable model = getModel(position);
        model.setPosition(position);
        holder.onBindHolder(model);
    }

    @Override
    public int getItemCount(){
        return models.size();
    }

    @Override
    public int getItemViewType(int position){
        return getModel(position).getViewType();
    }

    @Override
    public long getItemId(int position){
        return getModel(position).getItemId();
    }
}
