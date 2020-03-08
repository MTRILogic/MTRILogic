package com.mtrilogic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class SquareGridView extends GridView {
    private boolean portrait;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public SquareGridView(Context context, boolean portrait) {
        super(context);
        this.portrait = portrait;
    }

    public SquareGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareGridView);
        portrait = typedArray.getBoolean(R.styleable.SquareGridView_portrait, false);
        typedArray.recycle();
    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int side = portrait ? getMeasuredHeight() : getMeasuredWidth();
        setMeasuredDimension(side, side);
    }
}
