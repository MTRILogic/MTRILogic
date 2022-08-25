package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Paginable extends Modelable {
    private static final String PAGE_TITLE = "pageTitle", TAG_NAME = "tagName";

    private String pageTitle, tagName;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Paginable(){}

    public Paginable(String pageTitle, String tagName, long itemId, int viewType){
        super(itemId, viewType,true);
        this.pageTitle = pageTitle;
        this.tagName = tagName;
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected Paginable(Bundle data){
        super(data);
    }

    /*==============================================================================================
    PUBLIC FINAL METHODS
    ==============================================================================================*/

    public final String getPageTitle(){
        return pageTitle;
    }

    public final void setPageTitle(String pageTitle){
        this.pageTitle = pageTitle;
    }

    public final String getTagName(){
        return tagName;
    }

    public final void setTagName(String tagName){
        this.tagName = tagName;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        pageTitle = data.getString(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putString(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
    }
}