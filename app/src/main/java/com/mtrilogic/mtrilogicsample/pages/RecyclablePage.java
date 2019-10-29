package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.ListablePage;
import com.mtrilogic.mtrilogicsample.types.PageType;

@SuppressWarnings("unused")
public class RecyclablePage extends ListablePage {
    public static final Creator<RecyclablePage> CREATOR = new PaginableCreator<RecyclablePage>() {
        @Override
        public RecyclablePage getParcelable(Bundle data) {
            return new RecyclablePage(data);
        }

        @Override
        public RecyclablePage[] getParcelableArray(int size) {
            return new RecyclablePage[size];
        }
    };

    public RecyclablePage(){}

    public RecyclablePage(String pageTitle, String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.RECYCLABLE);
    }

    private RecyclablePage(Bundle data){
        super(data);
    }
}
