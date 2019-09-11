package com.mtrilogic.mtrilogicsample.pages;

import android.os.Bundle;
import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@SuppressWarnings("unused")
public class ExpandablePage extends Paginable{
    public static final Creator<ExpandablePage> CREATOR = new PaginableCreator<ExpandablePage>(){
        @Override
        public ExpandablePage getParcelable(Parcel src, ClassLoader loader){
            return new ExpandablePage(src, loader);
        }

        @Override
        public ExpandablePage[] getParcelableArray(int size){
            return new ExpandablePage[size];
        }
    };
    private static final String LIST = "list", IDX = "idx";
    private Listable groupListable;
    private Mapable childMapable;

    public ExpandablePage(){}

    public ExpandablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        groupListable = new Listable();
        childMapable = new Mapable();
    }

    private ExpandablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }

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

    @Override
    protected void restoreFromData(Bundle data){
        super.restoreFromData(data);
        childMapable = new Mapable(new LinkedHashMap<Modelable, Listable>());
        ArrayList<Modelable> groupModelableList = data.getParcelableArrayList(LIST);
        long groupIdx = data.getLong(IDX,0);
        if(groupModelableList != null){
            for(Modelable groupModelable : groupModelableList){
                long groupItemId = groupModelable.getItemId();
                ArrayList<Modelable> childModelableList = data.getParcelableArrayList(LIST + groupItemId);
                long childIdx = data.getLong(IDX + groupItemId,0);
                if(childModelableList == null){
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
    protected void saveToData(Bundle data){
        super.saveToData(data);
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
