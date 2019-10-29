package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.ListablePage;
import com.mtrilogic.mtrilogicsample.types.PageType;

@SuppressWarnings("unused")
public class InflatablePage extends ListablePage {
    public static final Creator<InflatablePage> CREATOR = new PaginableCreator<InflatablePage>() {
        @Override
        public InflatablePage getParcelable(Bundle data) {
            return new InflatablePage(data);
        }

        @Override
        public InflatablePage[] getParcelableArray(int size) {
            return new InflatablePage[size];
        }
    };

    public InflatablePage(){}

    public InflatablePage(String pageTitle, String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.INFLATABLE);
    }

    private InflatablePage(Bundle data){
        super(data);
    }
}
