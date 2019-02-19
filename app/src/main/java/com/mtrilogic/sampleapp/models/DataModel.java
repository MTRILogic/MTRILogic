package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ID;

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
    private long itemId;

    @SuppressWarnings("unused")
    public DataModel(){
        this(0);
    }

    @SuppressWarnings("WeakerAccess")
    public DataModel(long itemId){
        this.itemId = itemId;
        viewType = ID.NORMAL.DATA;
        enabled = true;
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
        return enabled;
    }

    @SuppressWarnings("unused")
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
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
        dest.writeInt(enabled ? 1 : 0);
        dest.writeInt(viewType);
        dest.writeLong(itemId);
    }

    private DataModel(Parcel src){
        title = src.readString();
        content = src.readString();
        icon = src.readInt();
        enabled = src.readInt() > 0;
        viewType = src.readInt();
        itemId = src.readLong();
    }
}
