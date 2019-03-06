package com.mtrilogic.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Paginable implements Parcelable{
    private String pageTitle, tag;
    private int viewType;
    private long itemId;

    protected abstract void onReadFromParcel(Parcel src);
    protected abstract void onWriteToParcel(Parcel dst, int flags);

    protected Paginable(){}

    public String getPageTitle(){
        return pageTitle;
    }

    @SuppressWarnings("WeakerAccess")
    public void setPageTitle(String pageTitle){
        this.pageTitle = pageTitle;
    }

    public String getTag(){
        return tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public int getViewType(){
        return viewType;
    }

    public void setViewType(int viewType){
        this.viewType = viewType;
    }

    public long getItemId(){
        return itemId;
    }

    public void setItemId(long itemId){
        this.itemId = itemId;
    }

    protected Paginable(Parcel src){
        onReadFromParcel(src);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        onWriteToParcel(dest,flags);
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
