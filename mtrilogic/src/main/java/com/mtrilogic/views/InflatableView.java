package com.mtrilogic.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.mtrilogic.classes.Base;

public class InflatableView extends ListView {
    private static final String INDEX = "index", TOP = "top";

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableView(Context context) {
        super(context);
    }

    public InflatableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InflatableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void restoreFromState(@NonNull Bundle state){
        int index = state.getInt(INDEX, Base.INVALID_POSITION);
        if (index == 0) {
            int top = state.getInt(TOP);
            setSelectionFromTop(index, top);
        }
    }

    public final void saveToState(@NonNull Bundle state){
        int index = getFirstVisiblePosition();
        if (index == 0){
            View view = getChildAt(0);
            int top = view != null ? view.getTop() - getPaddingTop() : 0;
            state.putInt(INDEX, index);
            state.putInt(TOP, top);
        }
    }
}
