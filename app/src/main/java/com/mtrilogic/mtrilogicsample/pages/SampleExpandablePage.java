package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.pages.ExpandablePage;

@SuppressWarnings("unused")
public class SampleExpandablePage extends ExpandablePage {
    public static final Creator<SampleExpandablePage> CREATOR = new PaginableCreator<SampleExpandablePage>() {
        @Override
        public SampleExpandablePage getParcelable(Bundle data) {
            return new SampleExpandablePage(data);
        }

        @Override
        public SampleExpandablePage[] getParcelableArray(int size) {
            return new SampleExpandablePage[size];
        }
    };

    public SampleExpandablePage(){}

    public SampleExpandablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleExpandablePage(Bundle data){
        super(data);
    }
}
