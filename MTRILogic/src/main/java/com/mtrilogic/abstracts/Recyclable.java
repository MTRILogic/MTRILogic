package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.ModelBindable;
import com.mtrilogic.interfaces.RecyclableItemListener;

@SuppressWarnings("unused")
public abstract class Recyclable<M extends Model> extends RecyclerView.ViewHolder implements ModelBindable {

    protected final RecyclableItemListener listener;

    private final Class<M> clazz;

    protected int position;
    protected M model;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public Recyclable(@NonNull Class<M> clazz, @NonNull View itemView, @NonNull RecyclableItemListener listener) {
        super(itemView);
        this.listener = listener;
        this.clazz = clazz;
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void bindItemView(){
        itemView.setOnClickListener(v -> listener.onRecyclableClick(itemView, model, position));
        itemView.setOnLongClickListener(v -> listener.onRecyclableLongClick(itemView, model, position));
        onBindItemView();
    }

    public void bindModel(@NonNull Model model, int position){
        this.model = clazz.cast(model);
        this.position = position;
        onBindModel();
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected final boolean autoDelete(){
        return listener.getModelListable().delete(model);
    }

    protected final void notifyChanged(){
        notifyItem(RecyclerView.Adapter::notifyItemChanged, RecyclerView.Adapter::notifyItemRangeChanged);
    }

    protected final void notifyInserted(){
        notifyItem(RecyclerView.Adapter::notifyItemInserted, RecyclerView.Adapter::notifyItemRangeInserted);
    }

    protected final void notifyDeleted(){
        notifyItem(RecyclerView.Adapter::notifyItemRemoved, RecyclerView.Adapter::notifyItemRangeRemoved);
    }

    protected final void notifyMoved(int fromPosition, int toPosition){
        listener.getRecyclableAdapter().notifyItemMoved(fromPosition, toPosition);
    }

    protected final void makeToast(String line){
        listener.onMakeToast(line);
    }

    /*==============================================================================================
    PRIVATE METHOD
    ==============================================================================================*/

    private void notifyItem(OnNotifyItemListener itemListener, OnNotifyItemRangeListener itemRangeListener){
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        int position = getAdapterPosition();
        itemListener.onNotifyItem(adapter, position);
        int count = listener.getModelListable().getCount();
        if (count > position) {
            itemRangeListener.onNotifyItemRange(adapter, position, count - position);
        }
    }

    /*==============================================================================================
    PRIVATE INTERFACES
    ==============================================================================================*/

    private interface OnNotifyItemListener{
        void onNotifyItem(RecyclableAdapter adapter, int position);
    }

    private interface  OnNotifyItemRangeListener{
        void onNotifyItemRange(RecyclableAdapter adapter, int position, int count);
    }
}
