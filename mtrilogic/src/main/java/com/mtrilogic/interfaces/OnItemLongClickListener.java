package com.mtrilogic.interfaces;

import android.view.View;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnItemLongClickListener extends OnItemClickListener {
    boolean onItemLongClick(View view, Modelable modelable, int position);
}
