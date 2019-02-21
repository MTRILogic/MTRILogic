package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;

public class ChildModel extends Modelable{
    public static final Parcelable.Creator<ChildModel> CREATOR = new Parcelable.Creator<ChildModel>(){
        @Override
        public ChildModel createFromParcel(Parcel source){
            return new ChildModel(source);
        }

        @Override
        public ChildModel[] newArray(int size){
            return new ChildModel[size];
        }
    };

    private String title, content;
    private boolean checked;
    private int icon, viewType;
    private long itemId;

    @SuppressWarnings("unused")
    public ChildModel(){}

    @SuppressWarnings("WeakerAccess")
    public ChildModel(long itemId){
        this.itemId = itemId;
        viewType = 0;
    }

    public boolean isChecked(){
        return checked;
    }

    @SuppressWarnings("unused")
    public void setChecked(boolean checked){
        this.checked = checked;
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

    @Override
    public boolean isEnabled(){
        return true;
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
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(icon);
        dest.writeInt(checked ? 1 : 0);
        dest.writeInt(viewType);
        dest.writeLong(itemId);
    }

    private ChildModel(Parcel src){
        title = src.readString();
        content = src.readString();
        icon = src.readInt();
        checked = src.readInt() != 0;
        viewType = src.readInt();
        itemId = src.readLong();
    }
}
