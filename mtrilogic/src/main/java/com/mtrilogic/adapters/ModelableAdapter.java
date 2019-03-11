package com.mtrilogic.adapters;

import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings({"unused","WeakerAccess"})
public class ModelableAdapter extends Modelable{
    public static final Creator<ModelableAdapter> CREATOR = new Creator<ModelableAdapter>(){
        @Override
        public ModelableAdapter createFromParcel(Parcel source){
            return new ModelableAdapter(source);
        }

        @Override
        public ModelableAdapter[] newArray(int size){
            return new ModelableAdapter[size];
        }
    };

    private int viewType;
    private boolean enabled;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public ModelableAdapter(){}

    public ModelableAdapter(int viewType){
        this(viewType,true);
    }

    public ModelableAdapter(boolean enabled){
        this(0,enabled);
    }

    public ModelableAdapter(int viewType, boolean enabled){
        this.viewType = viewType;
        this.enabled = enabled;
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private ModelableAdapter(Parcel in){
        super(in);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public void setViewType(int viewType){
        this.viewType = viewType;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public int getViewType(){
        return viewType;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

    // +++++++++++++++++| OVERRIDE PROTECTED METHODS |+++++++++++++++++++++++++

    @Override
    protected void onReadFromParcel(Parcel in){
        viewType = in.readInt();
        enabled = in.readInt() != 0;
    }

    @Override
    protected void onWriteToParcel(Parcel out){
        out.writeInt(viewType);
        out.writeInt(enabled ? 1 : 0);
    }
}
