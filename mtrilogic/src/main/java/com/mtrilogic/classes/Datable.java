package com.mtrilogic.classes;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public class Datable implements Parcelable {
    public static final Creator<Datable> CREATOR = new ClassLoaderCreator<Datable>() {
        @Override
        public Datable createFromParcel(Parcel source, ClassLoader loader) {
            return new Datable(source, loader);
        }

        @Override
        public Datable createFromParcel(Parcel source) {
            return new Datable(source, null);
        }

        @Override
        public Datable[] newArray(int size) {
            return new Datable[size];
        }
    };

    private Bundle data;

    public Datable(Bundle data){
        this.data = data;
    }

    private Datable(Parcel src, ClassLoader loader){
        if (src != null && loader != null){
            data = src.readBundle(loader);
        }
    }

    public Bundle getData() {
        return data;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
