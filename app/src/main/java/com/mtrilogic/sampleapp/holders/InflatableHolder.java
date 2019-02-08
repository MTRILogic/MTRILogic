package com.mtrilogic.sampleapp.holders;

import android.view.View;
import android.widget.TextView;

import com.mtrilogic.sampleapp.R;
import com.mtrilogic.views.SquareImageView;

public final class InflatableHolder{
    public TextView lblTitle, lblContent;
    public SquareImageView ivwIcon;

    public InflatableHolder(View view){
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ivwIcon = view.findViewById(R.id.ivw_icon);
    }
}
