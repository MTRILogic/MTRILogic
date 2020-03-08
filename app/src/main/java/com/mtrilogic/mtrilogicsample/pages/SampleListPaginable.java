package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.ListPaginable;

@SuppressWarnings("unused")
public class SampleListPaginable extends ListPaginable<Modelable> {
    public static final Creator<SampleListPaginable> CREATOR = new PaginableCreator<SampleListPaginable>() {
        @Override
        public SampleListPaginable getParcelable(@NonNull Bundle data) {
            return new SampleListPaginable(data);
        }

        @Override
        public SampleListPaginable[] getParcelableArray(int size) {
            return new SampleListPaginable[size];
        }
    };

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SampleListPaginable(){}

    public SampleListPaginable(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
    }

    // ================< PRIVATE CONSTRUCTORS >=====================================================

    private SampleListPaginable(@NonNull Bundle data){
        super(data);
    }
}
