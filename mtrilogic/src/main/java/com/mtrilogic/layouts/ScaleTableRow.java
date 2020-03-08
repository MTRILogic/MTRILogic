package com.mtrilogic.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TableRow;

import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class ScaleTableRow extends TableRow {
    private int scale;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ScaleTableRow(Context context, int scale) {
        super(context);
        this.scale = scale;
    }

    public ScaleTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleTableRow);
        scale = typedArray.getInt(R.styleable.ScaleTableRow_scale, 1);
        typedArray.recycle();
    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scale = scale > 0 ? scale : 1;
        int side = getMeasuredWidth();
        setMeasuredDimension(side, side / scale);
    }
}
