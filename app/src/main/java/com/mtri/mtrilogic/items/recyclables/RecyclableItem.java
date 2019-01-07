package com.mtri.mtrilogic.items.recyclables;

import android.content.Context;

import com.mtri.abstracts.Recyclable;
import com.mtri.interfaces.OnAdapterChangedListener;
import com.mtri.mtrilogic.holders.RecyclableHolder;
import com.mtri.mtrilogic.models.DataModel;

public class RecyclableItem extends Recyclable<RecyclableHolder,DataModel>{
    private DataModel model;

    public RecyclableItem(OnAdapterChangedListener listener, long id, DataModel model){
        super(listener,id);
        this.model = model;
    }

    @Override
    public void onBindHolder(RecyclableHolder holder, Context context){
        holder.lblTitle.setText(model.getTitle());
        holder.lblContent.setText(model.getContent());
        holder.ivwIcon.setImageResource(model.getIcon());
    }

    @Override
    public int getViewType(){
        return model.getViewType();
    }

    @Override
    public DataModel getModel(){
        return model;
    }
}
