package com.mtrilogic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mtrilogic.library.R;

@SuppressWarnings("unused")
public class SquareImageView extends AppCompatImageView{
    private boolean portrait;

    public SquareImageView(Context context, boolean portrait){
        super(context);
        this.portrait = portrait;
    }

    public SquareImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SquareImageView);
        portrait = typedArray.getBoolean(R.styleable.SquareImageView_portrait,false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int side = portrait ? getMeasuredHeight() : getMeasuredWidth();
        setMeasuredDimension(side,side);
    }
}