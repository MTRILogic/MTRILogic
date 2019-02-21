package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;

public class GroupModel extends Modelable{
    public static final Parcelable.Creator<GroupModel> CREATOR = new Parcelable.Creator<GroupModel>(){
        @Override
        public GroupModel createFromParcel(Parcel source){
            return new GroupModel(source);
        }

        @Override
        public GroupModel[] newArray(int size){
            return new GroupModel[size];
        }
    };

    private String title;
    private int viewType;
    private boolean checked;
    private long itemId;

    @SuppressWarnings("unused")
    public GroupModel(){}

    @SuppressWarnings("WeakerAccess")
    public GroupModel(long itemId){
        this.itemId = itemId;
        viewType = 0;
    }

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
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
        dest.writeInt(checked ? 1 : 0);
        dest.writeInt(viewType);
        dest.writeLong(itemId);
    }

    private GroupModel(Parcel src){
        title = src.readString();
        checked = src.readInt() != 0;
        viewType = src.readInt();
        itemId = src.readLong();
    }
}
