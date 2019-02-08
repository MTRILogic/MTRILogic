package com.mtrilogic.abstracts;

import android.os.Parcelable;

public abstract class Modelable implements Parcelable{
    @Override
    public int describeContents(){
        return 0;
    }
}
