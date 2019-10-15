package com.mtrilogic.abstracts.pages;

import android.os.Bundle;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class ExpandablePage extends Paginable {
    private static final String LIST = "list", IDX = "idx";
    private Listable groupListable;
    private Mapable childMapable;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ExpandablePage(){}

    public ExpandablePage(Bundle data){
        super(data);
    }

    public ExpandablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        groupListable = new Listable();
        childMapable = new Mapable();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Listable getGroupListable(){
        return groupListable;
    }

    public void setGroupListable(Listable groupListable){
        this.groupListable = groupListable;
    }

    public Mapable getChildMapable(){
        return childMapable;
    }

    public void setChildMapable(Mapable childMapable){
        this.childMapable = childMapable;
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onRestoreFromData(Bundle data){
        super.onRestoreFromData(data);
        childMapable = new Mapable(new LinkedHashMap<Modelable, Listable>());
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
                Listable childListable = new Listable(childModelableList, childIdx);
                childMapable.putListable(groupModelable, childListable);
            }
        }else {
            groupModelableList = new ArrayList<>();
        }
        groupListable = new Listable(groupModelableList, groupIdx);
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
            Listable childListable = childMapable.getListable(groupModelable);
            ArrayList<Modelable> childModelableList = childListable.getModelableList();
            long childIdx = childListable.getIdx();
            data.putParcelableArrayList(LIST + groupItemId, childModelableList);
            data.putLong(IDX + groupItemId, childIdx);
        }
    }
}
