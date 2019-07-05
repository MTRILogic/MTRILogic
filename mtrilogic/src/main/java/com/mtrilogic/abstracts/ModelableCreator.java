package com.mtrilogic.abstracts;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M>{

    public abstract M getParcelable(Parcel src, ClassLoader loader);

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
}
