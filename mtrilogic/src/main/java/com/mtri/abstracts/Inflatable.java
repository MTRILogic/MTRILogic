package com.mtri.abstracts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class Inflatable<M extends Modelable>{
    protected M model;
    protected long id;

    public abstract View getInflatableView(View view, ViewGroup parent, Context context);

    public Inflatable(long id){
        this.id = id;
        model = null;
    }

    public Inflatable(M model, long id){
        this.model = model;
        this.id = id;
    }

    public M getModel(){
        return model;
    }

    public long getItemId(){
        return id;
    }
}
