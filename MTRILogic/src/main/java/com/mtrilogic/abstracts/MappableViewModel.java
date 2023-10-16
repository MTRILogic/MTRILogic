package com.mtrilogic.abstracts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mtrilogic.classes.Mappable;

@SuppressWarnings("unused")
public abstract class MappableViewModel extends ViewModel {

    private MutableLiveData<Mappable<Model>> mappableLiveData;

    protected abstract void loadItems();

    public MappableViewModel() {
        super();
    }

    public LiveData<Mappable<Model>> getListableLiveData() {
        if (mappableLiveData == null) {
            mappableLiveData = new MutableLiveData<>();
            loadItems();
        }
        return mappableLiveData;
    }

    public Mappable<Model> getListable() {
        return mappableLiveData.getValue();
    }

    public void setListable(Mappable<Model> listable) {
        mappableLiveData.setValue(listable);
    }

    public void postListable(Mappable<Model> listable) {
        mappableLiveData.postValue(listable);
    }
}
