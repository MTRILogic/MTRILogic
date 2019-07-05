package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Modelable implements Parcelable{
    protected static final String ITEM_ID = "itemId", VIEW_TYPE = "viewType", ENABLED = "enabled";
    private Bundle data;
    private long itemId;
    private int viewType;
    private boolean enabled;

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |+++++++++++++++++++++++++++++++++

    protected abstract void restoreFromData(Bundle data);

    protected abstract void saveToData(Bundle data);

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public Modelable(){
        data = new Bundle();
    }

    public Modelable(long itemId, int viewType, boolean enabled){
        data = new Bundle();
        this.itemId = itemId;
        this.viewType = viewType;
        this.enabled = enabled;
    }

// ++++++++++++++++| PROTECTED CONSTRUCTORS |++++++++++++++++++++++++++++++++++

    protected Modelable(Parcel src, ClassLoader loader){
        restoreFromParcel(src, loader);
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

    public long getItemId(){
        return itemId;
    }

    public int getViewType(){
        return viewType;
    }

    public boolean isEnabled(){
        return enabled;
    }

// ++++++++++++++++| PROTECTED METHODS |+++++++++++++++++++++++++++++++++++++++

    protected void restoreFromParcel(Parcel src, ClassLoader loader){
        if(src != null && loader != null && (data = src.readBundle(loader)) != null){
            itemId = data.getLong(ITEM_ID);
            viewType = data.getInt(VIEW_TYPE);
            enabled = data.getBoolean(ENABLED);
            restoreFromData(data);
        }else {
            data = new Bundle();
        }
    }

    protected void saveToParcel(Parcel dest){
        data.putLong(ITEM_ID, itemId);
        data.putInt(VIEW_TYPE, viewType);
        data.putBoolean(ENABLED, enabled);
        saveToData(data);
        dest.writeBundle(data);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public final void writeToParcel(Parcel dest, int flags){
        saveToParcel(dest);
    }

    @Override
    public final int describeContents(){
        return 0;
    }
}
