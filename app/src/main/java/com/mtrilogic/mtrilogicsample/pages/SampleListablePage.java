package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.ListablePage;

@SuppressWarnings("unused")
public class SampleListablePage extends ListablePage {
    public static final Creator<SampleListablePage> CREATOR = new PaginableCreator<SampleListablePage>() {
        @Override
        public SampleListablePage getParcelable(Bundle data) {
            return new SampleListablePage(data);
        }

        @Override
        public SampleListablePage[] getParcelableArray(int size) {
            return new SampleListablePage[size];
        }
    };

    public SampleListablePage(){}

    public SampleListablePage(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    private SampleListablePage(@NonNull Bundle data){
        super(data);
    }
}
