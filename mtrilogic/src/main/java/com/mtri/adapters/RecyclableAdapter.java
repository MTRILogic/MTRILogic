package com.mtri.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mtri.abstracts.Recyclable;
import com.mtri.interfaces.OnNewHolderListener;

import java.util.List;

public class RecyclableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private OnNewHolderListener listener;
    private List<Recyclable> items;
    private Context context;

    public RecyclableAdapter(OnNewHolderListener listener, List<Recyclable> items, Context context){
        this.listener = listener;
        this.items = items;
        this.context = context;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return listener.onNewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        items.get(position).bindViewHolder(holder,context);
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    @Override
    public int getItemViewType(int position){
        return items.get(position).getViewType();
    }

    @Override
    public long getItemId(int position){
        return items.get(position).getItemId();
    }
}
