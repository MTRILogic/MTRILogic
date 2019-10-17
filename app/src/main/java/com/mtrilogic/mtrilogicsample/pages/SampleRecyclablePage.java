package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.pages.ListablePage;

@SuppressWarnings("unused")
public class SampleRecyclablePage extends ListablePage {
    public static final Creator<SampleRecyclablePage> CREATOR = new PaginableCreator<SampleRecyclablePage>() {
        @Override
        public SampleRecyclablePage getParcelable(Bundle data) {
            return new SampleRecyclablePage(data);
        }

        @Override
        public SampleRecyclablePage[] getParcelableArray(int size) {
            return new SampleRecyclablePage[size];
        }
    };

    public SampleRecyclablePage(){}

    public SampleRecyclablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleRecyclablePage(Bundle data){
        super(data);
    }
}
