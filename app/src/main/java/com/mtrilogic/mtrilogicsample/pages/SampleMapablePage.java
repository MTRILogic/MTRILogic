package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

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

    public SampleMapablePage(@NonNull String pageTitle, @NonNull String tagName, long itemId){
        super(pageTitle, tagName, itemId, PageType.EXPANDABLE);
    }

    private SampleMapablePage(@NonNull Bundle data){
        super(data);
    }
}
