package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class SimpleModel2 extends SimpleModel1 {
    private static final String TEXT2 = "text2";

    private String text2;

    public SimpleModel2() {
        super();
    }

    public SimpleModel2(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        text2 = data.getString(TEXT2);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putString(TEXT2, text2);
    }
}
