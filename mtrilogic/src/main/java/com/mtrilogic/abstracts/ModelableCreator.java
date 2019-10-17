package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M>{

    public abstract M getParcelable(Bundle data);

    public abstract M[] getParcelableArray(int size);

    @Override
    public final M createFromParcel(Parcel src, ClassLoader loader){
        return getParcelable(src, loader);
    }

    @Override
    public final M createFromParcel(Parcel src){
        return getParcelable(src,null);
    }

    @Override
    public final M[] newArray(int size){
        return getParcelableArray(size);
    }

    private M getParcelable(Parcel source, ClassLoader loader){
        Bundle data;
        if (source == null || loader == null || (data = source.readBundle(loader)) == null){
            data = new Bundle();
        }
        return getParcelable(data);
    }
}
