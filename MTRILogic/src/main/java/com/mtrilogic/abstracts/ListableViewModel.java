package com.mtrilogic.abstracts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ListableViewModel extends ViewModel {

    private MutableLiveData<Listable<Model>> listableLiveData;

    protected abstract void loadItems();

    public ListableViewModel() {
        super();
    }

    public LiveData<Listable<Model>> getListableLiveData() {
        if (listableLiveData == null) {
            listableLiveData = new MutableLiveData<>();
            loadItems();
        }
        return listableLiveData;
    }

    public Listable<Model> getListable() {
        return listableLiveData.getValue();
    }

    public void setListable(Listable<Model> listable) {
        listableLiveData.setValue(listable);
    }

    public void postListable(Listable<Model> listable) {
        listableLiveData.postValue(listable);
    }
}
