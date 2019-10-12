package com.mtrilogic.mtrilogicsample.pages;

import android.os.Parcel;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.pages.InflatablePage;

@SuppressWarnings("unused")
public class SampleInflatablePage extends InflatablePage {
    public static final Creator<SampleInflatablePage> CREATOR = new PaginableCreator<SampleInflatablePage>(){
        @Override
        public SampleInflatablePage getParcelable(Parcel src, ClassLoader loader){
            return new SampleInflatablePage(src, loader);
        }

        @Override
        public SampleInflatablePage[] getParcelableArray(int size){
            return new SampleInflatablePage[size];
        }
    };

    public SampleInflatablePage(){}

    public SampleInflatablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleInflatablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }
}
