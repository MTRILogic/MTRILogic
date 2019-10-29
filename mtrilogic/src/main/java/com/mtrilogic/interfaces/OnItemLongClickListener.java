package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnItemLongClickListener extends OnItemClickListener {
    void onItemLongClick(Modelable modelable, int position);
}
