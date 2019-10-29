package com.mtrilogic.abstracts;

import android.os.Bundle;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Paginable extends Modelable {
    private static final String PAGE_TITLE = "pageTitle", TAG_NAME = "tagName";
    private String pageTitle, tagName;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Paginable(){}

    public Paginable(Bundle data){
        super(data);
    }

    public Paginable(String pageTitle, String tagName, long itemId, int viewType){
        super(itemId, viewType,true);
        this.pageTitle = pageTitle;
        this.tagName = tagName;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public String getPageTitle(){
        return pageTitle;
    }

    public void setPageTitle(String pageTitle){
        this.pageTitle = pageTitle;
    }

    public String getTagName(){
        return tagName;
    }

    public void setTagName(String tagName){
        this.tagName = tagName;
    }

// ++++++++++++++++| PROTECTED OVERRIDE METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(Bundle data) {
        pageTitle = data.getString(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
        super.onRestoreFromData(data);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        data.putString(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
        super.onSaveToData(data);
    }
}
