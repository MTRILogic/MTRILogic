package com.mtrilogic.mtrilogicsample.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;

@SuppressWarnings({"unused","WeakerAccess"})
public class DataModel extends Modelable{
    public static final Creator<DataModel> CREATOR = new ModelableCreator<DataModel>() {
        @Override
        public DataModel getParcelable(Bundle data) {
            return new DataModel(data);
        }

        @Override
        public DataModel[] getParcelableArray(int size) {
            return new DataModel[size];
        }
    };
    private static final String TITLE = "title", CONTENT = "content", CHECKED = "checked";
    private String title, content;
    private boolean checked;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public DataModel(){}

    public DataModel(@NonNull Bundle data){
        super(data);
    }

    public DataModel(long itemId, boolean checked){
        this(itemId, ItemChildType.DATA, false);
    }

    public DataModel(long itemId, int viewType, boolean checked){
        super(itemId, viewType,true);
        this.checked = checked;
    }

    // ================< PUBLIC METHODS >===========================================================

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

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onRestoreFromData(Bundle data) {
        super.onRestoreFromData(data);
        title = data.getString(TITLE);
        content = data.getString(CONTENT);
        checked = data.getBoolean(CHECKED);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        super.onSaveToData(data);
        data.putString(TITLE, title);
        data.putString(CONTENT, content);
        data.putBoolean(CHECKED, checked);
    }
}
