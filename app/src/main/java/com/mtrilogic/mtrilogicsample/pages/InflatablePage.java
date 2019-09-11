package com.mtrilogic.mtrilogicsample.pages;

import android.os.Parcel;

import com.mtrilogic.abstracts.ListPaginable;
import com.mtrilogic.abstracts.PaginableCreator;

public class InflatablePage extends ListPaginable{
    public static final Creator<InflatablePage> CREATOR = new PaginableCreator<InflatablePage>(){
        @Override
        public InflatablePage getParcelable(Parcel src, ClassLoader loader){
            return new InflatablePage(src, loader);
        }

        @Override
        public InflatablePage[] getParcelableArray(int size){
            return new InflatablePage[size];
        }
    };

    public InflatablePage(){}

    public InflatablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private InflatablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }
}
