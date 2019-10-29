package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Modelable;

public interface ItemEventListener extends OnMakeToastListener {
    void onItemClick(Modelable modelable, int position);
    void onItemLongClick(Modelable modelable, int position);
}
