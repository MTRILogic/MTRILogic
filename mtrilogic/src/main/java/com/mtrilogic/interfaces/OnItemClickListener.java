package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnItemClickListener extends OnMakeToastListener {
    void onItemClick(Modelable modelable, int position);
}
