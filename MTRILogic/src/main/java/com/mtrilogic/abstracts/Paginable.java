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
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        pageTitle = data.getString(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        data.putString(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
    }
}