package com.mtrilogic.abstracts.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class InflatablePage extends Paginable {
    private static final String LIST = "list", IDX = "idx";
    private ArrayList<Modelable> modelableList;
    private long idx;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public InflatablePage(){}

    public InflatablePage(Bundle data){
        super(data);
    }

    public InflatablePage(String pageTitle, String tagName, long itemId, int viewType){
        this(pageTitle, tagName, itemId, viewType, new ArrayList<Modelable>(), 0);
    }

    public InflatablePage(String pageTitle, String tagName, long itemId, int viewType,
                          ArrayList<Modelable> modelableList, long idx){
        super(pageTitle, tagName, itemId, viewType);
        this.modelableList = modelableList;
        this.idx = idx;
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ArrayList<Modelable> getModelableList(){
        return modelableList;
    }

    public void setModelableList(ArrayList<Modelable> modelableList){
        this.modelableList = modelableList;
    }

    public long getIdx(){
        return idx;
    }

    public void setIdx(long idx){
        this.idx = idx;
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(Bundle data) {
        super.onRestoreFromData(data);
        modelableList = data.getParcelableArrayList(LIST);
        idx = data.getLong(IDX);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        super.onSaveToData(data);
        data.putParcelableArrayList(LIST, modelableList);
        data.putLong(IDX, idx);
    }
}