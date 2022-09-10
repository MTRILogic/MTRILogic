package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Mappable;

@SuppressWarnings("unused")
public abstract class MappablePage extends Page {
    private Mappable<Model> modelMappable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public MappablePage() {
        super();
    }

    public MappablePage(String pageTitle, String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
        modelMappable = new Mappable<>();
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected MappablePage(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public Mappable<Model> getModelableMapable() {
        return modelMappable;
    }

    public void setModelableMapable(Mappable<Model> modelableMappable) {
        this.modelMappable = modelableMappable;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        modelMappable = new Mappable<>(data);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        modelMappable.saveToData(data);
    }
}
