package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.interfaces.OnNotifyDataSetChangedListener;

public abstract class Inflatable<M extends Modelable>{
    protected OnNotifyDataSetChangedListener listener;
    protected long id;

    public abstract View getInflatableView(View view, ViewGroup parent, Context context);
    public abstract M getModel();

    public Inflatable(OnNotifyDataSetChangedListener listener, long id){
        this.listener = listener;
        this.id = id;
    }

    public long getItemId(){
        return id;
    }
}
