package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class MapPaginable<M extends Modelable> extends Paginable {
    private Listable<M> groupListable;
    private Mapable<M> childMapable;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public MapPaginable(){
        super();
    }

    public MapPaginable(@NonNull String pageTitle, long itemmId, int viewType){
        super(pageTitle, itemmId, viewType);
        groupListable = new Listable<>();
        childMapable = new Mapable<>();
    }

    // ================< PROTECTED CONSTRUCTORS >===================================================

    protected MapPaginable(@NonNull Bundle data){
        super(data);
    }

    // ================< PUBLIC METHODS >===========================================================

    public final Listable<M> getGroupListable(){
        return groupListable;
    }

    public final void setGroupListable(@NonNull Listable<M> groupListable){
        this.groupListable = groupListable;
    }

    public final Mapable<M> getChildMapable(){
        return childMapable;
    }

    public final void setChildMapable(@NonNull Mapable<M> childMapable){
        this.childMapable = childMapable;
    }

    // ================< PROTECTED METHODS >========================================================

    @Override
    protected void onRestoreFromData(@NonNull Bundle data){
        super.onRestoreFromData(data);
        childMapable = new Mapable<>();
        groupListable = new Listable<>(data, childMapable);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data){
        super.onSaveToData(data);
        if (groupListable != null && childMapable != null) {
            groupListable.saveToData(data, childMapable);
        }
    }
}
