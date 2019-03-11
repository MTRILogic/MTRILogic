package com.mtrilogic.sampleapp.models;

import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.sampleapp.viewtypes.DataViewType;

@SuppressWarnings({"unused","WeakerAccess"})
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

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public ImageModel(){}

    public ImageModel(int viewType){
        this(viewType,true);
    }

    public ImageModel(boolean enabled){
        this(DataViewType.IMAGE,enabled);
    }

    public ImageModel(int viewType, boolean enabled){
        this.viewType = viewType;
        this.enabled = enabled;
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private ImageModel(Parcel src){
        super(src);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

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

    public void setViewType(int viewType){
        this.viewType = viewType;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public int getViewType(){
        return viewType;
    }

    @Override
    public boolean isEnabled(){
        return false;
    }

    // +++++++++++++++++| OVERRIDE PROTECTED METHODS |+++++++++++++++++++++++++;

    @Override
    protected void onReadFromParcel(Parcel in){
        imageLink = in.readString();
        rating = in.readFloat();
        enabled = in.readInt() != 0;
        viewType = in.readInt();
    }

    @Override
    protected void onWriteToParcel(Parcel out){
        out.writeString(imageLink);
        out.writeFloat(rating);
        out.writeInt(enabled ? 1 :0);
        out.writeInt(viewType);
    }
}
