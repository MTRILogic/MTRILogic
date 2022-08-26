package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Mapable;

@SuppressWarnings("unused")
public abstract class MapablePaginable extends Paginable {
    private Mapable<Modelable> modelableMapable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public MapablePaginable() {
        super();
    }

    public MapablePaginable(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
        modelableMapable = new Mapable<>();
    }

    protected MapablePaginable(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public Mapable<Modelable> getModelableMapable() {
        return modelableMapable;
    }

    public void setModelableMapable(Mapable<Modelable> modelableMapable) {
        this.modelableMapable = modelableMapable;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        modelableMapable = new Mapable<>(data);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        modelableMapable.saveToData(data);
    }
}
