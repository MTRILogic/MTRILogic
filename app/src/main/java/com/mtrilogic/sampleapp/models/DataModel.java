package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ID;
import com.mtrilogic.sampleapp.viewtypes.DataViewType;

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
    private long itemId;

    @SuppressWarnings("unused")
    public DataModel(){}

    @SuppressWarnings("WeakerAccess")
    public DataModel(long itemId){
        this.itemId = itemId;
        viewType = DataViewType.DATA;
    }

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

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public int getViewType(){
        return viewType;
    }

    @Override
    public long getItemId(){
        return itemId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(icon);
        dest.writeInt(viewType);
        dest.writeLong(itemId);
    }

    private DataModel(Parcel src){
        title = src.readString();
        content = src.readString();
        icon = src.readInt();
        viewType = src.readInt();
        itemId = src.readLong();
    }
}
