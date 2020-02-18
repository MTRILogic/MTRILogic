package com.mtrilogic.mtrilogicsample.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ModelableCreator;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings({"unused"})
public class ImageModel extends DataModel{
    public static final Creator<ImageModel> CREATOR = new ModelableCreator<ImageModel>() {
        @Override
        public ImageModel getParcelable(Bundle data) {
            return new ImageModel(data);
        }

        @Override
        public ImageModel[] getParcelableArray(int size) {
            return new ImageModel[size];
        }
    };
    private static final String IMAGE_LINK = "imageLink", RATING = "rating";
    private String imageLink;
    private float rating;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ImageModel(){}

    public ImageModel(long itemId, boolean checked){
        super(itemId, ChildType.IMAGE, checked);
    }

    private ImageModel(@NonNull Bundle data){
        super(data);
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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

// ++++++++++++++++| PROTECTED OVERRIDE METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(Bundle data) {
        super.onRestoreFromData(data);
        imageLink = data.getString(IMAGE_LINK);
        rating = data.getFloat(RATING);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        super.onSaveToData(data);
        data.putString(IMAGE_LINK, imageLink);
        data.putFloat(RATING, rating);
    }
}
