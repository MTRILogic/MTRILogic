package com.mtrilogic.interfaces;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.views.InflatableView;

@SuppressWarnings("unused")
public interface InflatableAdapterListener extends OnItemLongClickListener{
    InflatableAdapter getInflatableAdapter();
    InflatableView getInflatableView();
}
