package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class ListPaginable<M extends Modelable> extends Paginable {

    private Listable<M> listable;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ListPaginable(){}

    public ListPaginable(@NonNull Bundle data){
        super(data);
    }

    public ListPaginable(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        listable = new Listable<>();
    }

    // ================< PUBLIC METHODS >===========================================================

    @NonNull
    public Listable<M> getListable() {
        return listable;
    }

    public void setListable(@NonNull Listable<M> listable) {
        this.listable = listable;
    }

    // ================< PROTECTED METHODS >========================================================

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        listable = new Listable<>();
        listable.restoreFromData(data);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        if (listable != null) {
            listable.saveToData(data);
        }
    }
}