package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

@SuppressWarnings({"unused"})
public final class RecyclableAdapter extends RecyclerView.Adapter<Recyclable<? extends Model>>{
    private final RecyclableAdapterListener listener;
    private final LayoutInflater inflater;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public RecyclableAdapter(@NonNull LayoutInflater inflater, @NonNull RecyclableAdapterListener listener){
        this.inflater = inflater;
        this.listener = listener;
        setHasStableIds(true);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Recyclable<? extends Model> onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Recyclable<? extends Model> item = listener.getRecyclable(viewType, inflater, parent);
        item.bindItemView();
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable item, int position){
        Model model = getModel(position);
        item.bindModel(model, position);
    }

    @Override
    public int getItemCount(){
        return getModelListable().getCount();
    }

    @Override
    public int getItemViewType(int position){
        return getModel(position).getViewType();
    }

    @Override
    public long getItemId(int position){
        return getModel(position).getItemId();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private Listable<Model> getModelListable(){
        return listener.getModelListable();
    }

    private Model getModel(int position){
        return getModelListable().get(position);
    }
}
