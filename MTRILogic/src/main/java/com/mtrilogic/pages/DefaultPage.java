package com.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.PaginableCreator;

@SuppressWarnings("unused")
public class DefaultPage extends Paginable {
    public static final Creator<DefaultPage> CREATOR = new PaginableCreator<DefaultPage>() {
        @Override
        public DefaultPage createFromData(Bundle data) {
            return new DefaultPage(data);
        }

        @Override
        public DefaultPage[] newArray(int size) {
            return new DefaultPage[size];
        }
    };

    public DefaultPage() {
        super();
    }

    public DefaultPage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    protected DefaultPage(Bundle data) {
        super(data);
    }
}
