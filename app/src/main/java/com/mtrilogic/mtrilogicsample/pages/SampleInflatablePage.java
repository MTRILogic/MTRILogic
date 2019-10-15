package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.pages.InflatablePage;

@SuppressWarnings("unused")
public class SampleInflatablePage extends InflatablePage {
    public static final Creator<SampleInflatablePage> CREATOR = new PaginableCreator<SampleInflatablePage>() {
        @Override
        public SampleInflatablePage getParcelable(Bundle data) {
            return new SampleInflatablePage(data);
        }

        @Override
        public SampleInflatablePage[] getParcelableArray(int size) {
            return new SampleInflatablePage[size];
        }
    };

    public SampleInflatablePage(){}

    public SampleInflatablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleInflatablePage(Bundle data){
        super(data);
    }
}
