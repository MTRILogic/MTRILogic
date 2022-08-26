package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ListablePaginable extends Paginable {
    private Listable<Modelable> modelableListable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public ListablePaginable() {
        super();
    }

    public ListablePaginable(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
        modelableListable = new Listable<>();
    }

    protected ListablePaginable(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public Listable<Modelable> getModelableListable() {
        return modelableListable;
    }

    public void setModelableListable(Listable<Modelable> modelableListable) {
        this.modelableListable = modelableListable;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        modelableListable = new Listable<>(data);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        modelableListable.saveToData(data);
    }
}
