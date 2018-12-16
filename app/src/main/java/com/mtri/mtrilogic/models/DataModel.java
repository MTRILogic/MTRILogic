package com.mtri.mtrilogic.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtri.abstracts.Modelable;
import com.mtri.mtrilogic.R;

public class DataModel extends Modelable{
    public static final Parcelable.Creator<DataModel> CREATOR = new Parcelable.Creator<DataModel>(){
        @Override
        public DataModel createFromParcel(Parcel source){
            return new DataModel(source);
        }

        @Override
        public DataModel[] newArray(int size){
            return new DataModel[size];
        }
    };

    private String title, content;
    private int icon;

    public DataModel(){
        super(R.id.DATA);
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    private DataModel(Parcel src){
        super(src);
        title = src.readString();
        content = src.readString();
        icon = src.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(icon);
    }
}
