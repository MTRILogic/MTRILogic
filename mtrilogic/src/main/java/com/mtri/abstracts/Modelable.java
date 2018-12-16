package com.mtri.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Modelable implements Parcelable{
    private int classId;

    public Modelable(int classId){
        this.classId = classId;
    }

    public Modelable(Parcel src){
        classId = src.readInt();
    }

    public int getClassId(){
        return classId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(classId);
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
