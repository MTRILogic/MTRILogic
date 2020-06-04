package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.RecyclableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class RecyclableAdapter extends RecyclerView.Adapter<Recyclable<?, ?>>{
    private Listable<Modelable> listable;
    private RecyclableListener listener;
    private LayoutInflater inflater;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public RecyclableAdapter(@NonNull Context context, @NonNull RecyclableListener listener,
                             @NonNull Listable<Modelable> listable){
        inflater = LayoutInflater.from(context);
        this.listable = listable;
        this.listener = listener;
        setHasStableIds(true);
    }

    // ================< PUBLIC METHODS >===========================================================

    public Listable<Modelable> getListable() {
        return listable;
    }

    public boolean setListable(Listable<Modelable> listable) {
        if (this.listable != listable) {
            this.listable = listable;
            return true;
        }
        return false;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @NonNull
    @Override
    public Recyclable<?, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return listener.getRecyclable(viewType, inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable holder, int position){
        Modelable modelable = getModelable(position);
        holder.bindModel(modelable, position);
    }

    @Override
    public int getItemCount(){
        return getModelableList().size();
    }

    @Override
    public int getItemViewType(int position){
        return getModelable(position).getViewType();
    }

    @Override
    public long getItemId(int position){
        return getModelable(position).getItemId();
    }

    // ================< PRIVATE METHODS >==========================================================

    private Modelable getModelable(int position){
        return getModelableList().get(position);
    }

    private ArrayList<Modelable> getModelableList(){
        return listable.getList();
    }
}
