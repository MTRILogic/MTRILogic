package com.mtrilogic.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.MappablePage;
import com.mtrilogic.abstracts.PageCreator;

@SuppressWarnings("unused")
public class DefaultMappablePage extends MappablePage {
    public static final Creator<DefaultMappablePage> CREATOR = new PageCreator<DefaultMappablePage>() {
        @Override
        public DefaultMappablePage createFromData(Bundle data) {
            return new DefaultMappablePage(data);
        }

        @Override
        public DefaultMappablePage[] newArray(int size) {
            return new DefaultMappablePage[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultMappablePage() {
        super();
    }

    public DefaultMappablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultMappablePage(Bundle data) {
        super(data);
    }
}
