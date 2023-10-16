package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.OnIterationListener;

@SuppressWarnings("unused")
public abstract class ModelTransformation<MIn extends Model , MOut extends Model> implements OnIterationListener<MIn> {

    private final Listable<MOut> outListable = new Listable<>();
    private final Listable<MIn> inListable;
    private long idx;

    @NonNull
    public abstract MOut onFactoryIteration(MIn item, long idx);

    public ModelTransformation(Listable<MIn> inListable){
        this.inListable = inListable;
    }

    public ModelTransformation<MIn, MOut> build(){
        inListable.iterateList(this);
        if (idx > 0){
            outListable.setIdx(idx);
        }
        return this;
    }

    public Listable<MOut> getListable(){
        return outListable;
    }

    @Override
    public void onIteration(@NonNull MIn item) {
        if (outListable.append(onFactoryIteration(item, idx))){
            idx++;
        }
    }
}
