package com.mtrilogic.sampleapp.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mtrilogic.interfaces.OnClickPositionListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.views.SquareImageView;

public final class RecyclableHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView lblTitle, lblContent;
    public SquareImageView ivwIcon;
    private OnClickPositionListener listener;

    public RecyclableHolder(@NonNull View itemView, OnClickPositionListener listener){
        super(itemView);
        itemView.setOnClickListener(this);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ivwIcon = itemView.findViewById(R.id.ivw_icon);
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        listener.onClickPosition(view,getLayoutPosition());
    }
}
