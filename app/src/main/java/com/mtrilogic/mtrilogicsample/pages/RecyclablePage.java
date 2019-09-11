package com.mtrilogic.mtrilogicsample.pages;

import android.os.Parcel;

import com.mtrilogic.abstracts.ListPaginable;
import com.mtrilogic.abstracts.PaginableCreator;

public class RecyclablePage extends ListPaginable{
    public static final Creator<RecyclablePage> CREATOR = new PaginableCreator<RecyclablePage>(){
        @Override
        public RecyclablePage getParcelable(Parcel src, ClassLoader loader){
            return new RecyclablePage(src, loader);
        }

        @Override
        public RecyclablePage[] getParcelableArray(int size){
            return new RecyclablePage[size];
        }
    };

    public RecyclablePage(){}

    public RecyclablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private RecyclablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }
}
