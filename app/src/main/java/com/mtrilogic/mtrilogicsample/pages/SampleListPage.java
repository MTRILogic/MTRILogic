package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.abstracts.ListPaginable;

@SuppressWarnings("unused")
public class SampleListPage extends ListPaginable<Modelable> {
    public static final Creator<SampleListPage> CREATOR = new PaginableCreator<SampleListPage>() {
        @Override
        public SampleListPage createFromData(@NonNull Bundle data) {
            return new SampleListPage(data);
        }

        @Override
        public SampleListPage[] newArray(int size) {
            return new SampleListPage[size];
        }
    };

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SampleListPage(){}

    public SampleListPage(String pageTitle, long itemId, int viewType) {
        super(pageTitle, itemId, viewType);
    }

    // ================< PRIVATE CONSTRUCTORS >=====================================================

    private SampleListPage(@NonNull Bundle data){
        super(data);
    }
}
