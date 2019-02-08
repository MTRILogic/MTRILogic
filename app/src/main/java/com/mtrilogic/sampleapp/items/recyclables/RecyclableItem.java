package com.mtrilogic.sampleapp.items.recyclables;

import android.content.Context;

import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;
import com.mtrilogic.sampleapp.holders.RecyclableHolder;
import com.mtrilogic.sampleapp.models.DataModel;

public class RecyclableItem extends Recyclable<RecyclableHolder,DataModel>{
    private DataModel model;

    public RecyclableItem(OnNotifyDataSetChangedListener listener, long id, DataModel model){
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
    public DataModel getModel(){
        return model;
    }
}
