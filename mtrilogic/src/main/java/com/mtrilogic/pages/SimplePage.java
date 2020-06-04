package com.mtrilogic.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.PaginableCreator;

@SuppressWarnings("unused")
public class SimplePage extends Paginable {
    public static final Creator<SimplePage> CREATOR = new PaginableCreator<SimplePage>() {
        @Override
        public SimplePage createFromData(@NonNull Bundle data) {
            return new SimplePage(data);
        }

        @Override
        public SimplePage[] newArray(int size) {
            return new SimplePage[size];
        }
    };

    private static final String TEXT = "text", COLOR = "color", BACK_COLOR = "backColor";

    private int color, backColor;
    private String text;

    public SimplePage() {
        super();
    }

    public SimplePage(@NonNull String pageTitle, long itemId, int viewType) {
        super(pageTitle, itemId, viewType);
    }

    private SimplePage(@NonNull Bundle data) {
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
