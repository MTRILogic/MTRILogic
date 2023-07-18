package com.mtrilogic.mtrilogic.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.MappablePage;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.PageCreator;

@SuppressWarnings("unused")
public class DefaultMappablePage<M extends Model> extends MappablePage<M> {

    public static final Creator<DefaultMappablePage<? extends Model>> CREATOR = new PageCreator<DefaultMappablePage<? extends Model>>() {
        @Override
        public DefaultMappablePage<? extends Model> createFromData(Bundle data) {
            return new DefaultMappablePage<>(data);
        }

        @Override
        public DefaultMappablePage<? extends Model>[] newArray(int size) {
            return new DefaultMappablePage<?>[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultMappablePage() {
        super();
    }

    public DefaultMappablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultMappablePage(Bundle data) {
        super(data);
    }
}
