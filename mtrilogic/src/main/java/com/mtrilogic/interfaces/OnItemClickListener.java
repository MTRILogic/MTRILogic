package com.mtrilogic.interfaces;

import android.view.View;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnItemClickListener extends OnMakeToastListener {
    void onItemClick(View view, Modelable modelable, int position);
}
