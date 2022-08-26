package com.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.MapablePaginable;
import com.mtrilogic.abstracts.PaginableCreator;

@SuppressWarnings("unused")
public class DefaultMapablePage extends MapablePaginable {
    public static final Creator<DefaultMapablePage> CREATOR = new PaginableCreator<DefaultMapablePage>() {
        @Override
        public DefaultMapablePage createFromData(Bundle data) {
            return new DefaultMapablePage(data);
        }

        @Override
        public DefaultMapablePage[] newArray(int size) {
            return new DefaultMapablePage[size];
        }
    };

    public DefaultMapablePage() {
        super();
    }

    public DefaultMapablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    private DefaultMapablePage(Bundle data) {
        super(data);
    }
}
