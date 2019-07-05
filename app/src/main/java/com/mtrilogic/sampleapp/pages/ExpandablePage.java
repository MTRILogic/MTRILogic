package com.mtrilogic.sampleapp.pages;

import android.os.Bundle;
import android.os.Parcel;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.PaginableCreator;
import com.mtrilogic.classes.Listable;

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
    private Listable<Modelable> groupModelableList;
    private LinkedHashMap<Long, Listable<Modelable>> childModelableMap;

    public ExpandablePage(){}

    public ExpandablePage(String pageTitle, String tagName, long itemId, int viewType){
        super(pageTitle, tagName, itemId, viewType);
        groupModelableList = new Listable<>(new ArrayList<Modelable>());
        childModelableMap = new LinkedHashMap<>();
    }

    private ExpandablePage(Parcel src, ClassLoader loader){
        super(src, loader);
    }

    public Listable<Modelable> getGroupModelableList(){
        return groupModelableList;
    }

    public void setGroupModelableList(Listable<Modelable> groupModelableList){
        this.groupModelableList = groupModelableList;
    }

    public LinkedHashMap<Long, Listable<Modelable>> getChildModelableMap(){
        return childModelableMap;
    }

    public void setChildModelableMap(LinkedHashMap<Long, Listable<Modelable>> childModelableMap){
        this.childModelableMap = childModelableMap;
    }

    @Override
    protected void restoreFromData(Bundle data){
        super.restoreFromData(data);
        groupModelableList = new Listable<>();
        groupModelableList.list = data.getParcelableArrayList(LIST);
        groupModelableList.idx = data.getLong(IDX);

        childModelableMap = new LinkedHashMap<>();
        if(groupModelableList != null){
            for(Modelable modelable : groupModelableList.list){
                long itemId = modelable.getItemId();
                Listable<Modelable> childModelableList = new Listable<>();
                childModelableList.list = data.getParcelableArrayList(LIST + itemId);
                childModelableList.idx = data.getLong(IDX + itemId,0);
                childModelableMap.put(itemId, childModelableList);
            }
        }
    }

    @Override
    protected void saveToData(Bundle data){
        super.saveToData(data);
        data.putParcelableArrayList(LIST, groupModelableList.list);
        data.putLong(IDX, groupModelableList.idx);

        for(Modelable modelable : groupModelableList.list){
            long itemId = modelable.getItemId();
            Listable<Modelable> childModelableList = childModelableMap.get(itemId);
            if(childModelableList != null){
                data.putParcelableArrayList(LIST + itemId, childModelableList.list);
                data.putLong(IDX + itemId, childModelableList.idx);
            }else {
                data.putParcelableArrayList(LIST + itemId, new ArrayList<Modelable>());
            }
        }
    }
}
