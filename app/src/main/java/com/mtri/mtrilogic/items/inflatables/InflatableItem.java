package com.mtri.mtrilogic.items.inflatables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtri.abstracts.Inflatable;
import com.mtri.interfaces.OnAdapterChangedListener;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.holders.InflatableHolder;
import com.mtri.mtrilogic.models.DataModel;

public class InflatableItem extends Inflatable<DataModel>{
    private DataModel model;

    public InflatableItem(OnAdapterChangedListener listener, long id, DataModel model){
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
    public int getViewType(){
        return model.getViewType();
    }

    @Override
    public DataModel getModel(){
        return model;
    }
}
