package com.mtrilogic.abstracts;

import android.os.Parcelable;

public abstract class Modelable implements Parcelable{
    public abstract boolean isEnabled();
    public abstract int getViewType();
    public abstract long getItemId();
    private int groupPosition;
    private int childPosition;
    private int position;

    public int getGroupPosition(){
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition){
        this.groupPosition = groupPosition;
    }

    public int getChildPosition(){
        return childPosition;
    }

    public void setChildPosition(int childPosition){
        this.childPosition = childPosition;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    @Override
    public int describeContents(){
        return 0;
    }
}
