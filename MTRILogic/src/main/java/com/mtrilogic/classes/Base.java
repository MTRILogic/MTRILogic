package com.mtrilogic.classes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public final class Base{
    public static final long INVALID_ID = -1;
    public static final int INVALID_POSITION = -1;

    private static final String TAG = "MainMTRI", INDEX = "index", TOP = "top";

    public static void restoreTopIndex(@NonNull AbsListView lvwItems, Bundle state){
        if (state != null) {
            int index = state.getInt(INDEX, Base.INVALID_POSITION);
            if (index != Base.INVALID_POSITION) {
                int top = state.getInt(TOP);
                lvwItems.post(() -> lvwItems.setSelectionFromTop(index, top));
            }
        }
    }

    public static void saveTopIndex(@NonNull AbsListView lvwItems, Bundle state){
        if (state != null) {
            int index = lvwItems.getFirstVisiblePosition();
            View view = lvwItems.getChildAt(0);
            int top = view != null ? view.getTop() - lvwItems.getPaddingTop() : 0;
            state.putInt(INDEX, index);
            state.putInt(TOP, top);
        }
    }

    public static void makeLog(String line){
        Log.d(TAG, "LOG: " + line);
    }
}
