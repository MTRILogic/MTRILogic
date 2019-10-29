package com.mtrilogic.abstracts;

import android.os.Bundle;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class MapablePage extends Paginable {
    private static final String LIST = "list", IDX = "idx";
    private Listable<Modelable> groupListable;
    private Mapable<Modelable> childMapable;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public MapablePage(){}

    public MapablePage(Bundle data){
        super(data);
    }

    public MapablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        groupListable = new Listable<>();
        childMapable = new Mapable<>();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Listable<Modelable> getGroupListable(){
        return groupListable;
    }

    public void setGroupListable(Listable<Modelable> groupListable){
        this.groupListable = groupListable;
    }

    public Mapable<Modelable> getChildMapable(){
        return childMapable;
    }

    public void setChildMapable(Mapable<Modelable> childMapable){
        this.childMapable = childMapable;
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(Bundle data){
        super.onRestoreFromData(data);
        childMapable = new Mapable<>(new LinkedHashMap<Modelable, Listable<Modelable>>());
        ArrayList<Modelable> groupModelableList = data.getParcelableArrayList(LIST);
        long groupIdx = data.getLong(IDX, 0);
        if(groupModelableList != null){
            for(Modelable groupModelable : groupModelableList){
                long groupItemId = groupModelable.getItemId();
                ArrayList<Modelable> childModelableList = data.getParcelableArrayList(LIST + groupItemId);
                long childIdx = data.getLong(IDX + groupItemId, 0);
                if(childModelableList == null) {
                    childModelableList = new ArrayList<>();
                }
                Listable<Modelable> childListable = new Listable<>(childModelableList, childIdx);
                childMapable.putListable(groupModelable, childListable);
            }
        }else {
            groupModelableList = new ArrayList<>();
        }
        groupListable = new Listable<>(groupModelableList, groupIdx);
    }

    @Override
    protected void onSaveToData(Bundle data){
        super.onSaveToData(data);
        ArrayList<Modelable> groupModelableList = groupListable.getModelableList();
        long groupIdx = groupListable.getIdx();
        data.putParcelableArrayList(LIST, groupModelableList);
        data.putLong(IDX, groupIdx);
        for(Modelable groupModelable : groupModelableList){
            long groupItemId = groupModelable.getItemId();
            Listable<Modelable> childListable = childMapable.getListable(groupModelable);
            ArrayList<Modelable> childModelableList = childListable.getModelableList();
            long childIdx = childListable.getIdx();
            data.putParcelableArrayList(LIST + groupItemId, childModelableList);
            data.putLong(IDX + groupItemId, childIdx);
        }
    }
}
