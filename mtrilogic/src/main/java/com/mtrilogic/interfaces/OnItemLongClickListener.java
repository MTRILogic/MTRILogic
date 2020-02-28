package com.mtrilogic.interfaces;

import android.view.View;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnItemLongClickListener extends OnItemClickListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    boolean onItemLongClick(View view, Modelable modelable, int position);
}
