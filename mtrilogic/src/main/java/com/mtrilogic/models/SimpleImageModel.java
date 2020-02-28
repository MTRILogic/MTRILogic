package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings("unused")
public class SimpleImageModel extends Modelable {

    public static final Creator<SimpleImageModel> CREATOR = new ModelableCreator<SimpleImageModel>() {
        @Override
        public SimpleImageModel getParcelable(Bundle data) {
            return new SimpleImageModel(data);
        }

        @Override
        public SimpleImageModel[] getParcelableArray(int size) {
            return new SimpleImageModel[size];
        }
    };

    private static final String IMAGE = "image";

    private int image;

    public SimpleImageModel() {
        super();
    }

    public SimpleImageModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    private SimpleImageModel(@NonNull Bundle data) {
        super(data);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    protected void onRestoreFromData(Bundle data) {
        super.onRestoreFromData(data);
        image = data.getInt(IMAGE);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        super.onSaveToData(data);
        data.putInt(IMAGE, image);
    }
}
