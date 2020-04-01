package com.mtrilogic.mtrilogicsample.extras;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;

public class Utils {

    private static final int[] LINKS = {
            R.string.link1,
            R.string.link2,
            R.string.link3,
            R.string.link4,
            R.string.link5
    };

    // ================< PUBLIC STATIC METHODS >====================================================

    public static Modelable getNewModelable(int viewType, @NonNull Context context){
        switch (viewType) {
            case ItemChildType.DATA:
                DataModel model = new DataModel();
                model.setViewType(viewType);
                return model;
            case ItemChildType.IMAGE:
                return getImageModel(viewType, context);
        }
        return null;
    }

    // ================< PRIVATE STATIC METHODS >===================================================

    @NonNull
    private static ImageModel getImageModel(int viewType, @NonNull Context context){
        ImageModel model = new ImageModel();
        model.setViewType(viewType);
        model.setImageLink(context.getString(LINKS[getRandomInt()]));
        model.setRating(getRandomInt());
        return model;
    }

    private static int getRandomInt(){
        return (int)(Math.random() * 4) + 1;
    }
}
