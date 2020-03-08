package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.MapPaginable;
import com.mtrilogic.mtrilogicsample.types.FragmentableType;

@SuppressWarnings("unused")
public class SampleMapPaginable extends MapPaginable<Modelable> {
    public static final Creator<SampleMapPaginable> CREATOR = new PaginableCreator<SampleMapPaginable>() {
        @Override
        public SampleMapPaginable getParcelable(@NonNull Bundle data) {
            return new SampleMapPaginable(data);
        }

        @Override
        public SampleMapPaginable[] getParcelableArray(int size) {
            return new SampleMapPaginable[size];
        }
    };

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SampleMapPaginable(){}

    public SampleMapPaginable(@NonNull String pageTitle, @NonNull String tagName, long itemId){
        super(pageTitle, tagName, itemId, FragmentableType.EXPANDABLE);
    }

    // ================< PRIVATE CONSTRUCTORS >=====================================================

    private SampleMapPaginable(@NonNull Bundle data){
        super(data);
    }
}
