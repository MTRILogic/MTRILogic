package com.mtrilogic.pages;

import android.os.Bundle;
import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class InflatablePage extends Paginable {
    private static final String LIST = "list", IDX = "idx";
    private ArrayList<Modelable> modelableList;
    private long idx;

    public InflatablePage(){}

    public InflatablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        modelableList = new ArrayList<>();
    }

    protected InflatablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }

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

    @Override
    protected void restoreFromData(Bundle data){
        super.restoreFromData(data);
        modelableList = data.getParcelableArrayList(LIST);
        idx = data.getLong(IDX);
    }

    @Override
    protected void saveToData(Bundle data){
        super.saveToData(data);
        data.putParcelableArrayList(LIST, modelableList);
        data.putLong(IDX, idx);
    }
}