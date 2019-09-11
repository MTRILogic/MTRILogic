package com.mtrilogic.mtrilogicsample.models;

import android.os.Bundle;
import android.os.Parcel;

import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings({"unused"})
public class ImageModel extends DataModel{
    public static final Creator<ImageModel> CREATOR = new ModelableCreator<ImageModel>(){
        @Override
        public ImageModel getParcelable(Parcel src, ClassLoader loader){
            return null;
        }

        @Override
        public ImageModel[] getParcelableArray(int size){
            return new ImageModel[size];
        }
    };
    private static final String IMAGE_LINK = "imageLink", RATING = "rating";
    private String imageLink;
    private float rating;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public ImageModel(){}

    public ImageModel(long itemId, int viewType){
        super(itemId, viewType);
    }

// ++++++++++++++++| PRIVATE CONSTRUCTORS |++++++++++++++++++++++++++++++++++++

    private ImageModel(Parcel src, ClassLoader loader){
        super(src, loader);
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

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

// ++++++++++++++++| PROTECTED OVERRIDE METHODS |++++++++++++++++++++++++++++++

    @Override
    protected void restoreFromData(Bundle data){
        super.restoreFromData(data);
        imageLink = data.getString(IMAGE_LINK);
        rating = data.getFloat(RATING);
    }

    @Override
    protected void saveToData(Bundle data){
        super.saveToData(data);
        data.putString(IMAGE_LINK, imageLink);
        data.putFloat(RATING, rating);
    }
}
