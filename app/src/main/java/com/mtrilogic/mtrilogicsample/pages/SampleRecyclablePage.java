package com.mtrilogic.mtrilogicsample.pages;

import android.os.Parcel;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.pages.InflatablePage;

@SuppressWarnings("unused")
public class SampleRecyclablePage extends InflatablePage {
    public static final Creator<SampleRecyclablePage> CREATOR = new PaginableCreator<SampleRecyclablePage>(){
        @Override
        public SampleRecyclablePage getParcelable(Parcel src, ClassLoader loader){
            return new SampleRecyclablePage(src, loader);
        }

        @Override
        public SampleRecyclablePage[] getParcelableArray(int size){
            return new SampleRecyclablePage[size];
        }
    };

    public SampleRecyclablePage(){}

    public SampleRecyclablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleRecyclablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }
}
