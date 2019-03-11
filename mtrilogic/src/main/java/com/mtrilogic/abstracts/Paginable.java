package com.mtrilogic.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Paginable implements Parcelable{
    private static int idx = 0;
    private String tag;
    private long itemId;

    // +++++++++++++++++| PUBLIC ABSTRACTS METHODS |+++++++++++++++++++++++++++

    public abstract String getPageTitle();
    public abstract int getViewType();

    // +++++++++++++++++| PROTECTED ABSTRACTS METHODS |++++++++++++++++++++++++

    protected abstract void onReadFromParcel(Parcel in);
    protected abstract void onWriteToParcel(Parcel out);

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public Paginable(){
        itemId = idx++;
    }

    // +++++++++++++++++| PROTECTED CONSTRUCTORS |+++++++++++++++++++++++++++++

    protected Paginable(Parcel in){
        onReadFromParcel(in);
        tag = in.readString();
        itemId = in.readLong();
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public long getItemId(){
        return itemId;
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public void writeToParcel(Parcel dest, int flags){
        onWriteToParcel(dest);
        dest.writeString(tag);
        dest.writeLong(itemId);
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
