package com.mtrilogic.sampleapp.models;

import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ID;

public class ImageModel extends Modelable{
    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>(){
        @Override
        public ImageModel createFromParcel(Parcel source){
            return new ImageModel(source);
        }

        @Override
        public ImageModel[] newArray(int size){
            return new ImageModel[size];
        }
    };
    private String imageLink;
    private float rating;
    private int viewType;
    private boolean enabled;
    private long itemId;

    @SuppressWarnings("unused")
    public ImageModel(){
        this(0);
    }

    @SuppressWarnings("WeakerAccess")
    public ImageModel(long itemId){
        this.itemId = itemId;
        viewType = ID.NORMAL.IMAGE;
        enabled = true;
    }

    public String getImageLink(){
        return imageLink;
    }

    public void setImageLink(String imageLink){
        this.imageLink = imageLink;
    }

    public float getRating(){
        return rating;
    }

    public void setRating(float rating){
        this.rating = rating;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

    @SuppressWarnings("unused")
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    @Override
    public long getItemId(){
        return itemId;
    }

    @Override
    public int getViewType(){
        return viewType;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(imageLink);
        dest.writeFloat(rating);
        dest.writeInt(viewType);
        dest.writeInt(enabled ? 1 : 0);
        dest.writeLong(itemId);
    }

    private ImageModel(Parcel source){
        imageLink = source.readString();
        rating = source.readFloat();
        viewType = source.readInt();
        enabled = source.readInt() != 0;
        itemId = source.readLong();
    }
}
