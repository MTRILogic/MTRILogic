package com.mtri.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Modelable implements Parcelable{
    private int viewType;

    public Modelable(int viewType){
        this.viewType = viewType;
    }

    public Modelable(Parcel src){
        viewType = src.readInt();
    }

    public int getViewType(){
        return viewType;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(viewType);
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
