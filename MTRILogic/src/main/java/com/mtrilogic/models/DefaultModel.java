package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings("unused")
public class DefaultModel extends Modelable {
    public static final Creator<DefaultModel> CREATOR = new ModelableCreator<DefaultModel>() {
        @Override
        protected DefaultModel createFromData(Bundle data) {
            return new DefaultModel(data);
        }

        @Override
        public DefaultModel[] getNewArray(int size) {
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
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        title = data.getString(TITLE);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putString(TITLE, title);
    }
}
