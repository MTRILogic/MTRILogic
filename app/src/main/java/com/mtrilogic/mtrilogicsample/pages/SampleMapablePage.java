package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.MapablePage;
import com.mtrilogic.mtrilogicsample.types.PageType;

@SuppressWarnings("unused")
public class SampleMapablePage extends MapablePage {
    public static final Creator<SampleMapablePage> CREATOR = new PaginableCreator<SampleMapablePage>() {
        @Override
        public SampleMapablePage getParcelable(Bundle data) {
            return new SampleMapablePage(data);
        }

        @Override
        public SampleMapablePage[] getParcelableArray(int size) {
            return new SampleMapablePage[size];
        }
    };

    public SampleMapablePage(){}

    public SampleMapablePage(String pageTitle, String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.EXPANDABLE);
    }

    private SampleMapablePage(Bundle data){
        super(data);
    }
}
