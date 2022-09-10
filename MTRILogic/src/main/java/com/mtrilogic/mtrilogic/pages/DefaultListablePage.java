package com.mtrilogic.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.ListablePage;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.PageCreator;

@SuppressWarnings("unused")
public class DefaultListablePage extends ListablePage<Model> {
    public static final Creator<DefaultListablePage> CREATOR = new PageCreator<DefaultListablePage>() {
        @Override
        public DefaultListablePage createFromData(Bundle data) {
            return null;
        }

        @Override
        public DefaultListablePage[] newArray(int size) {
            return new DefaultListablePage[0];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultListablePage() {
        super();
    }

    public DefaultListablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected DefaultListablePage(Bundle data) {
        super(data);
    }
}
