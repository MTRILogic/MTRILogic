package com.mtrilogic.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.mtrilogic.classes.Datable;

public class InflateExpandableView extends ExpandableListView {
    private static final String STATE = "state", INDEX = "index", TOP = "top";

    public InflateExpandableView(Context context) {
        super(context);
    }

    public InflateExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRestoreInstanceState(final Parcelable state) {
        if (state != null) {
            Datable datable = (Datable) state;
            Bundle data = datable.getData();
            super.onRestoreInstanceState(data.getParcelable(STATE));
            int index = data.getInt(INDEX, INVALID_POSITION);
            if (index == 0) {
                int top = data.getInt(TOP);
                setSelectionFromTop(index, top);
            }
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle data = new Bundle();
        data.putParcelable(STATE, super.onSaveInstanceState());
        int index = getFirstVisiblePosition();
        if (index == 0){
            View view = getChildAt(0);
            int top = view != null ? view.getTop() - getPaddingTop() : 0;
            data.putInt(INDEX, index);
            data.putInt(TOP, top);
        }
        return new Datable(data);
    }
}
