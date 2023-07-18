package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.OnIterationListener;

import java.util.ArrayList;

@SuppressWarnings("unused")
public abstract class ListableFactory<MIn extends Model , MOut extends Model> implements OnIterationListener<MIn> {

    private final ArrayList<MOut> list;

    private long idx;

    @NonNull
    public abstract MOut onFactoryIteration(MIn item, long idx);

    public ListableFactory(){
        list = new ArrayList<>();
    }

    public ListableFactory<MIn, MOut> build(Listable<MIn> listable){
        listable.iterateList(this);
        return this;
    }

    public Listable<MOut> getListable(){
        return new Listable<>(list, idx);
    }

    @Override
    public void onIteration(@NonNull MIn item) {
        if (list.add(onFactoryIteration(item, idx))){
            idx++;
        }
    }
}
