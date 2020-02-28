package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings("unused")
public class SimpleTextModel extends Modelable {

    public static final Creator<SimpleTextModel> CREATOR = new ModelableCreator<SimpleTextModel>() {
        @Override
        public SimpleTextModel getParcelable(Bundle data) {
            return new SimpleTextModel(data);
        }

        @Override
        public SimpleTextModel[] getParcelableArray(int size) {
            return new SimpleTextModel[size];
        }
    };

    private static final String TEXT = "text";

    private String text;

    public SimpleTextModel() {
        super();
    }

    public SimpleTextModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    private SimpleTextModel(@NonNull Bundle data) {
        super(data);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void onRestoreFromData(Bundle data) {
        super.onRestoreFromData(data);
        text = data.getString(TEXT);
    }

    @Override
    protected void onSaveToData(Bundle data) {
        super.onSaveToData(data);
        data.putString(TEXT, text);
    }
}
