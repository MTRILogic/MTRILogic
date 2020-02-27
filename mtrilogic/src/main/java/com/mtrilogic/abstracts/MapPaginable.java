package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class MapPaginable<M extends Modelable> extends Paginable {
    private Listable<M> groupListable;
    private Mapable<M> childMapable;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public MapPaginable(){}

    public MapPaginable(@NonNull Bundle data){
        super(data);
    }

    public MapPaginable(@NonNull String pageTitle, @NonNull String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        groupListable = new Listable<>();
        childMapable = new Mapable<>();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Listable<M> getGroupListable(){
        return groupListable;
    }

    public void setGroupListable(@NonNull Listable<M> groupListable){
        this.groupListable = groupListable;
    }

    public Mapable<M> getChildMapable(){
        return childMapable;
    }

    public void setChildMapable(@NonNull Mapable<M> childMapable){
        this.childMapable = childMapable;
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(@NonNull Bundle data){
        super.onRestoreFromData(data);
        childMapable = new Mapable<>();
        groupListable = new Listable<>();
        groupListable.restoreFromData(data, childMapable);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data){
        super.onSaveToData(data);
        if (groupListable != null && childMapable != null) {
            groupListable.saveToData(data, childMapable);
        }
    }
}
