package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.MapablePage;
import com.mtrilogic.mtrilogicsample.types.PageType;

@SuppressWarnings("unused")
public class ExpandablePage extends MapablePage {
    public static final Creator<ExpandablePage> CREATOR = new PaginableCreator<ExpandablePage>() {
        @Override
        public ExpandablePage getParcelable(Bundle data) {
            return new ExpandablePage(data);
        }

        @Override
        public ExpandablePage[] getParcelableArray(int size) {
            return new ExpandablePage[size];
        }
    };

    public ExpandablePage(){}

    public ExpandablePage(String pageTitle, String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.EXPANDABLE);
    }

    private ExpandablePage(Bundle data){
        super(data);
    }
}
