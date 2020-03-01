package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Paginable extends Modelable {

    private static final String PAGE_TITLE = "pageTitle", TAG_NAME = "tagName", PAGE_WIDTH = "pageWidth";

    private String pageTitle, tagName;
    private float pageWidth;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Paginable(){
        super();
    }

    public Paginable(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(itemId, viewType,true);
        this.pageTitle = pageTitle;
        this.tagName = tagName;
        pageWidth = 1f;
    }

    // ================< PROTECTED CONSTRUCTORS >===================================================

    protected Paginable(@NonNull Bundle data){
        super(data);
    }

    // ================< PUBLIC METHODS >===========================================================

    public final String getPageTitle(){
        return pageTitle;
    }

    public final void setPageTitle(@NonNull String pageTitle){
        this.pageTitle = pageTitle;
    }

    public final String getTagName(){
        return tagName;
    }

    public final void setTagName(@NonNull String tagName){
        this.tagName = tagName;
    }

    public final float getPageWidth() {
        return pageWidth;
    }

    public final void setPageWidth(float pageWidth) {
        this.pageWidth = pageWidth;
    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        pageTitle = data.getString(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
        pageWidth = data.getFloat(PAGE_WIDTH);
        super.onRestoreFromData(data);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        data.putString(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
        data.putFloat(PAGE_WIDTH, pageWidth);
        super.onSaveToData(data);
    }
}
