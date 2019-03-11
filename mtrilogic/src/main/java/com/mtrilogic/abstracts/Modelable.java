package com.mtrilogic.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Modelable implements Parcelable{
    private static int idx = 0;
    private int groupPosition;
    private int childPosition;
    private int position;
    private long itemId;

    // +++++++++++++++++| PUBLIC ABSTRACT METHODS |++++++++++++++++++++++++++++

    public abstract int getViewType();
    public abstract boolean isEnabled();

    // +++++++++++++++++| PROTECTED ABSTRACT METHODS |+++++++++++++++++++++++++

    protected abstract void onReadFromParcel(Parcel in);
    protected abstract void onWriteToParcel(Parcel out);

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public Modelable(){
        itemId = idx++;
    }

    // +++++++++++++++++| PROTECTED CONSTRUCTORS |+++++++++++++++++++++++++++++

    public Modelable(Parcel in){
        onReadFromParcel(in);
        itemId = in.readLong();
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public long getItemId(){
        return itemId;
    }

    public int getGroupPosition(){
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition){
        this.groupPosition = groupPosition;
    }

    public int getChildPosition(){
        return childPosition;
    }

    public void setChildPosition(int childPosition){
        this.childPosition = childPosition;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public void writeToParcel(Parcel dest, int flags){
        onWriteToParcel(dest);
        dest.writeLong(itemId);
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
