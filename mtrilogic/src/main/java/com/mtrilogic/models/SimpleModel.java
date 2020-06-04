package com.mtrilogic.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.ModelableCreator;

@SuppressWarnings("unused")
public class SimpleModel extends Modelable {
    public static final Creator<SimpleModel> CREATOR = new ModelableCreator<SimpleModel>() {
        @Override
        public SimpleModel createFromData(@NonNull Bundle data) {
            return new SimpleModel(data);
        }

        @Override
        public SimpleModel[] newArray(int size) {
            return new SimpleModel[size];
        }
    };

    private static final String TEXT = "text", COLOR = "color", BACK_COLOR = "backColor";

    private String text;
    private int color, backColor;

    public SimpleModel() {
        super();
    }

    public SimpleModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    private SimpleModel(@NonNull Bundle data) {
        super(data);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        text = data.getString(TEXT);
        color = data.getInt(COLOR);
        backColor = data.getInt(BACK_COLOR);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putString(TEXT, text);
        data.putInt(COLOR, color);
        data.putInt(BACK_COLOR, backColor);
    }
}
