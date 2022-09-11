package com.mtrilogic.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.ListablePage;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.PageCreator;

@SuppressWarnings("unused")
public class DefaultListablePage<M extends Model> extends ListablePage<M> {
    public static final Creator<DefaultListablePage<? extends Model>> CREATOR = new PageCreator<DefaultListablePage<? extends Model>>() {
        @Override
        public DefaultListablePage<? extends Model> createFromData(Bundle data) {
            return new DefaultListablePage<>(data);
        }

        @Override
        public DefaultListablePage<? extends Model>[] newArray(int size) {
            return new DefaultListablePage<?>[size];
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
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultListablePage(Bundle data) {
        super(data);
    }
}
