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

    public static Modelable getNewModelable(Context context, int viewType, long idx, boolean checked){
        switch (viewType) {
            case ItemChildType.DATA:
                return new DataModel(idx, checked);
            case ItemChildType.IMAGE:
                return getImageModel(context, idx, checked);
        }
        return null;
    }

    @NonNull
    private static ImageModel getImageModel(@NonNull Context context, long idx, boolean checked){
        ImageModel model = new ImageModel(idx, checked);
        model.setImageLink(context.getString(LINKS[getRandomInt()]));
        model.setRating(getRandomInt());
        return model;
    }

    private static int getRandomInt(){
        return (int)(Math.random() * 4) + 1;
    }
}
