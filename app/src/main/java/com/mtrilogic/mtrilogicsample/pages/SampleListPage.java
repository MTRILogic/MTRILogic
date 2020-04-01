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
        public SampleListPage getParcelable(@NonNull Bundle data) {
            return new SampleListPage(data);
        }

        @Override
        public SampleListPage[] getParcelableArray(int size) {
            return new SampleListPage[size];
        }
    };

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SampleListPage(){}

    public SampleListPage(String pageTitle, String tagName, int viewType) {
        super(pageTitle, tagName, viewType);
    }

    // ================< PRIVATE CONSTRUCTORS >=====================================================

    private SampleListPage(@NonNull Bundle data){
        super(data);
    }
}
