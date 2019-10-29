package com.mtrilogic.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.mtrilogic.classes.Base;

public class ExpandableView extends ExpandableListView {
    private static final String INDEX = "index", TOP = "top";

    public ExpandableView(Context context) {
        super(context);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void restoreFromState(Bundle state){
        int index = state.getInt(INDEX, Base.INVALID_POSITION);
        if (index == 0) {
            int top = state.getInt(TOP);
            setSelectionFromTop(index, top);
        }
    }

    public void saveToState(Bundle state){
        int index = getFirstVisiblePosition();
        if (index == 0){
            View view = getChildAt(0);
            int top = view != null ? view.getTop() - getPaddingTop() : 0;
            state.putInt(INDEX, index);
            state.putInt(TOP, top);
        }
    }
}
