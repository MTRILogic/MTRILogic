package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.MapPaginable;
import com.mtrilogic.mtrilogicsample.types.FragmentableType;

@SuppressWarnings("unused")
public class SampleMapPage extends MapPaginable<Modelable> {
    public static final Creator<SampleMapPage> CREATOR = new PaginableCreator<SampleMapPage>() {
        @Override
        public SampleMapPage createFromData(@NonNull Bundle data) {
            return new SampleMapPage(data);
        }

        @Override
        public SampleMapPage[] newArray(int size) {
            return new SampleMapPage[size];
        }
    };

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SampleMapPage(){}

    public SampleMapPage(@NonNull String pageTitle, long itemId){
        super(pageTitle, itemId, FragmentableType.EXPANDABLE);
    }

    // ================< PRIVATE CONSTRUCTORS >=====================================================

    private SampleMapPage(@NonNull Bundle data){
        super(data);
    }
}
