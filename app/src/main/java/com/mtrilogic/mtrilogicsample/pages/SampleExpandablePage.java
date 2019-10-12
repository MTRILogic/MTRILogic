package com.mtrilogic.mtrilogicsample.pages;

import android.os.Parcel;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.pages.ExpandablePage;

@SuppressWarnings("unused")
public class SampleExpandablePage extends ExpandablePage {
    public static final Creator<SampleExpandablePage> CREATOR = new PaginableCreator<SampleExpandablePage>(){
        @Override
        public SampleExpandablePage getParcelable(Parcel src, ClassLoader loader){
            return new SampleExpandablePage(src, loader);
        }

        @Override
        public SampleExpandablePage[] getParcelableArray(int size){
            return new SampleExpandablePage[size];
        }
    };

    public SampleExpandablePage(){}

    public SampleExpandablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleExpandablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }
}
