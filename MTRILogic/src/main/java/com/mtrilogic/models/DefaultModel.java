package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings("unused")
public class DefaultModel extends Modelable {
    public static final Creator<DefaultModel> CREATOR = new ModelableCreator<DefaultModel>() {
        @Override
        public DefaultModel createFromData(Bundle data) {
            return new DefaultModel(data);
        }

        @Override
        public DefaultModel[] newArray(int size) {
            return new DefaultModel[size];
        }
    };

    private static final String TITLE= "title";

    private String title;

    public DefaultModel() {
        super();
    }

    public DefaultModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    private DefaultModel(Bundle data) {
        super(data);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        title = data.getString(TITLE);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        data.putString(TITLE, title);
    }
}
