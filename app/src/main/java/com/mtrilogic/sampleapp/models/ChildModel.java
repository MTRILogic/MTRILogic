package com.mtrilogic.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings({"unused","WeakerAccess"})
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
    private boolean checked, enabled;
    private int icon, viewType;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public ChildModel(){}

    public ChildModel(int viewType){
        this(viewType,true);
    }

    public ChildModel(boolean enabled){
        this(0,enabled);
    }

    public ChildModel(int viewType, boolean enabled){
        this.viewType = viewType;
        this.enabled = enabled;
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private ChildModel(Parcel src){
        super(src);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public boolean isChecked(){
        return checked;
    }

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
        return enabled;
    }

    // +++++++++++++++++| OVERRIDE PROTECTED METHODS |+++++++++++++++++++++++++

    @Override
    protected void onReadFromParcel(Parcel in){
        title = in.readString();
        content = in.readString();
        icon = in.readInt();
        checked = in.readInt() != 0;
        enabled = in.readInt() != 0;
        viewType = in.readInt();
    }

    @Override
    protected void onWriteToParcel(Parcel out){
        out.writeString(title);
        out.writeString(content);
        out.writeInt(icon);
        out.writeInt(checked ? 1 : 0);
        out.writeInt(enabled ? 1 : 0);
        out.writeInt(viewType);
    }
}
