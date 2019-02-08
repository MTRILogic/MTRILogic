package com.mtrilogic.sampleapp.items.inflatables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.holders.InflatableHolder;
import com.mtrilogic.sampleapp.models.DataModel;

public class InflatableItem extends Inflatable<DataModel>{
    private DataModel model;

    public InflatableItem(OnNotifyDataSetChangedListener listener, long id, DataModel model){
        super(listener,id);
        this.model = model;
    }

    @Override
    public View getInflatableView(View view, ViewGroup parent, Context context){
        InflatableHolder holder = null;
        if(view != null){
            Object tag = view.getTag();
            if(tag instanceof InflatableHolder){
                holder = (InflatableHolder)tag;
            }
        }
        if(holder == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);
            holder = new InflatableHolder(view);
            view.setTag(holder);
        }
        holder.lblTitle.setText(model.getTitle());
        holder.lblContent.setText(model.getContent());
        holder.ivwIcon.setImageResource(model.getIcon());
        return view;
    }

    @Override
    public DataModel getModel(){
        return model;
    }
}
