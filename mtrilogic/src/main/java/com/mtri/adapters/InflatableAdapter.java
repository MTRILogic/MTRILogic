package com.mtri.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mtri.abstracts.Inflatable;

import java.util.List;

public class InflatableAdapter<T extends Inflatable> extends BaseAdapter{
    private List<T> items;
    private Context context;

    public InflatableAdapter(List<T> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public T getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return getItem(position).getInflatableView(convertView,parent,context);
    }
}
