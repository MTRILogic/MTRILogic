package com.mtrilogic.sampleapp.models;

import android.os.Bundle;
import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings({"unused","WeakerAccess"})
public class DataModel extends Modelable{
    public static final Creator<DataModel> CREATOR = new ModelableCreator<DataModel>(){
        @Override
        public DataModel getParcelable(Parcel src, ClassLoader loader){
            return new DataModel(src, loader);
        }

        @Override
        public DataModel[] getParcelableArray(int size){
            return new DataModel[size];
        }
    };
    private static final String TITLE = "title", CONTENT = "content", CHECKED = "checked";
    private String title, content;
    private boolean checked;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public DataModel(){}

    public DataModel(long itemId, int viewType){
        super(itemId, viewType,true);
    }

// ++++++++++++++++| PROTECTED CONSTRUCTORS |++++++++++++++++++++++++++++++++++

    protected DataModel(Parcel src, ClassLoader loader){
        super(src, loader);
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

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

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

// ++++++++++++++++| PROTECTED OVERRIDE METHODS |++++++++++++++++++++++++++++++

    @Override
    protected void restoreFromData(Bundle data){
        title = data.getString(TITLE);
        content = data.getString(CONTENT);
        checked = data.getBoolean(CHECKED);
    }

    @Override
    protected void saveToData(Bundle data){
        data.putString(TITLE, title);
        data.putString(CONTENT, content);
        data.putBoolean(CHECKED, checked);
    }
}
