package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public abstract class ModelableCreator<M extends Modelable> implements Parcelable.ClassLoaderCreator<M>{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    public abstract M createFromData(Bundle data);

    // ================< PUBLIC FINAL OVERRIDE METHODS >============================================

    @Override
    public final M createFromParcel(Parcel src, ClassLoader loader){
        return getParcelable(src, loader);
    }

    @Override
    public final M createFromParcel(Parcel src){
        return getParcelable(src,null);
    }

    // ================< PRIVATE METHODS >==========================================================

    private M getParcelable(Parcel source, ClassLoader loader){
        Bundle data = null;
        if (source != null && loader != null){
            data = source.readBundle(loader);
        }
        return createFromData(data);
    }
}
