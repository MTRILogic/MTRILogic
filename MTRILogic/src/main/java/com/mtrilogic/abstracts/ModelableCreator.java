package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M>{

    /*==============================================================================================
    PROTECTED ABSTRACT METHODS
    ==============================================================================================*/

    protected abstract M createFromData(Bundle data);
    protected abstract M[] getNewArray(int size);

    /*==============================================================================================
    PUBLIC OVERRIDE CONSTRUCTORS
    ==============================================================================================*/

    @Override
    public final M createFromParcel(Parcel src, ClassLoader loader){
        if (src != null && loader != null){
            return createFromData(src.readBundle(loader));
        }
        return null;
    }

    @Override
    public final M createFromParcel(Parcel src){
        return null;
    }

    @Override
    public M[] newArray(int size) {
        return getNewArray(size);
    }
}
