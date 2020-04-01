package com.mtrilogic.mtrilogicsample.states;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.abstracts.Statable;

public class PageState extends Statable {
    private MutableLiveData<Listable<Paginable>> listableLiveData;
    private Listable<Paginable> listable;

    // ================< PUBLIC METHODS >===========================================================

    @NonNull
    public Listable<Paginable> getListable() {
        return listable;
    }

    @NonNull
    public LiveData<Listable<Paginable>> getListableLiveData() {
        if (listableLiveData == null){
            listableLiveData = new MutableLiveData<>();
            listable = new Listable<>();
        }
        return listableLiveData;
    }

    public void setUpdate(){
        listableLiveData.setValue(listable);
    }

    public void postUpdate(){
        listableLiveData.postValue(listable);
    }
}
