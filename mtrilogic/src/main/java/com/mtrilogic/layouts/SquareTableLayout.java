package com.mtrilogic.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TableLayout;

import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class SquareTableLayout extends TableLayout {
    private boolean portrait;

    public SquareTableLayout(Context context, boolean portrait) {
        super(context);
        this.portrait = portrait;
    }

    public SquareTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareTableLayout);
        portrait = typedArray.getBoolean(R.styleable.SquareTableLayout_portrait, false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int side = portrait ? getMeasuredHeight() : getMeasuredWidth();
        setMeasuredDimension(side, side);
    }
}
