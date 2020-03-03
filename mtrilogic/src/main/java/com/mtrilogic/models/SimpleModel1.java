package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings({"unused", "WeakerAccess"})
public class SimpleModel1 extends Modelable {
    public static final Creator<SimpleModel1> CREATOR = new ModelableCreator<SimpleModel1>() {
        @Override
        public SimpleModel1 getParcelable(@NonNull Bundle data) {
            return new SimpleModel1(data);
        }

        @Override
        public SimpleModel1[] getParcelableArray(int size) {
            return new SimpleModel1[size];
        }
    };

    private static final String TEXT1 = "text1";

    private String text1;

    public SimpleModel1() {
        super();
    }

    public SimpleModel1(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    private SimpleModel1(@NonNull Bundle data) {
        super(data);
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        text1 = data.getString(TEXT1);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putString(TEXT1, text1);
    }
}
