package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Paginable extends Modelable {

    private static final String PAGE_TITLE = "pageTitle", TAG_NAME = "tagName";
    private String pageTitle, tagName;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Paginable(){}

    public Paginable(@NonNull Bundle data){
        super(data);
    }

    public Paginable(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(itemId, viewType,true);
        this.pageTitle = pageTitle;
        this.tagName = tagName;
    }

    // ================< PUBLIC METHODS >===========================================================

    @NonNull
    public String getPageTitle(){
        return pageTitle;
    }

    public void setPageTitle(@NonNull String pageTitle){
        this.pageTitle = pageTitle;
    }

    @NonNull
    public String getTagName(){
        return tagName;
    }

    public void setTagName(@NonNull String tagName){
        this.tagName = tagName;
    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        pageTitle = data.getString(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
        super.onRestoreFromData(data);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        data.putString(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
        super.onSaveToData(data);
    }
}
