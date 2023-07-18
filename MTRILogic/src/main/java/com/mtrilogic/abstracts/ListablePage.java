package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ListablePage<M extends Model> extends Page {

    private Listable<M> listable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public ListablePage() {
        super();
    }

    public ListablePage(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType) {
        super(pageTitle, tagName, itemId, viewType);
        listable = new Listable<>();
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected ListablePage(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public final Listable<M> getListable() {
        return listable;
    }

    public final void setListable(Listable<M> listable) {
        this.listable = listable;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        listable = new Listable<>(data);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        listable.saveToData(data);
    }
}
