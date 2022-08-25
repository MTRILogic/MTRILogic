package com.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.ListablePaginable;
import com.mtrilogic.abstracts.PaginableCreator;

@SuppressWarnings("unused")
public class DefaultListablePage extends ListablePaginable {
    public static final Creator<DefaultListablePage> CREATOR = new PaginableCreator<DefaultListablePage>() {
        @Override
        protected DefaultListablePage createFromData(Bundle data) {
            return null;
        }

        @Override
        protected DefaultListablePage[] getNewArray(int size) {
            return new DefaultListablePage[0];
        }
    };

    public DefaultListablePage() {
        super();
    }

    public DefaultListablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    private DefaultListablePage(Bundle data) {
        super(data);
    }
}
