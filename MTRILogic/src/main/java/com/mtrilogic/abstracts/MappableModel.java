package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Mappable;

@SuppressWarnings("unused")
public abstract class MappableModel<M extends Model> extends Model{

    private static final String MAPPABLE = "mappable";

    private Mappable<M> mappable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public MappableModel() {
        super();
    }

    public MappableModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
        mappable = new Mappable<>();
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected MappableModel(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public final Mappable<M> getMappable() {
        return mappable;
    }

    public final void setMappable(Mappable<M> mappable) {
        this.mappable = mappable;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        mappable = new Mappable<>(data, MAPPABLE);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        mappable.saveToData(data, MAPPABLE);
    }
}
