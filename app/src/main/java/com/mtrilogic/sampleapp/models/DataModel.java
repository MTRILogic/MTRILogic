package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.sampleapp.viewtypes.DataViewType;

@SuppressWarnings({"unused","WeakerAccess"})
public class DataModel extends Modelable{
    public static final Parcelable.Creator<DataModel> CREATOR = new Parcelable.Creator<DataModel>(){
        @Override
        public DataModel createFromParcel(Parcel source){
            return new DataModel(source);
        }

        @Override
        public DataModel[] newArray(int size){
            return new DataModel[size];
        }
    };

    private String title, content;
    private int icon, viewType;
    private boolean enabled;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public DataModel(){}

    public DataModel(int viewType){
        this(viewType,true);
    }

    public DataModel(boolean enabled){
        this(DataViewType.DATA,enabled);
    }

    public DataModel(int viewType, boolean enabled){
        this.viewType = viewType;
        this.enabled = enabled;
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private DataModel(Parcel src){
        super(src);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

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
        title = in.readString();
        content = in.readString();
        icon = in.readInt();
        enabled = in.readInt() != 0;
        viewType = in.readInt();
    }

    @Override
    protected void onWriteToParcel(Parcel out){
        out.writeString(title);
        out.writeString(content);
        out.writeInt(icon);
        out.writeInt(enabled ? 1 : 0);
        out.writeInt(viewType);
    }
}
