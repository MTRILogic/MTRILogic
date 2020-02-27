package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.MapPaginable;
import com.mtrilogic.mtrilogicsample.types.PageType;

@SuppressWarnings("unused")
public class SampleMapPaginable extends MapPaginable<Modelable> {
    public static final Creator<SampleMapPaginable> CREATOR = new PaginableCreator<SampleMapPaginable>() {
        @Override
        public SampleMapPaginable getParcelable(Bundle data) {
            return new SampleMapPaginable(data);
        }

        @Override
        public SampleMapPaginable[] getParcelableArray(int size) {
            return new SampleMapPaginable[size];
        }
    };

    public SampleMapPaginable(){}

    public SampleMapPaginable(@NonNull String pageTitle, @NonNull String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.EXPANDABLE);
    }

    private SampleMapPaginable(@NonNull Bundle data){
        super(data);
    }
}
