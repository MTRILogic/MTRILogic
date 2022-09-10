package com.mtrilogic.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.abstracts.PageCreator;

@SuppressWarnings("unused")
public class DefaultPage extends Page {
    public static final Creator<DefaultPage> CREATOR = new PageCreator<DefaultPage>() {
        @Override
        public DefaultPage createFromData(Bundle data) {
            return new DefaultPage(data);
        }

        @Override
        public DefaultPage[] newArray(int size) {
            return new DefaultPage[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultPage() {
        super();
    }

    public DefaultPage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultPage(Bundle data) {
        super(data);
    }
}
