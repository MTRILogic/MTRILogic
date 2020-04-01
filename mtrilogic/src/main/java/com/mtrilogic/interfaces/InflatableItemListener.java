package com.mtrilogic.interfaces;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.views.InflatableView;

@SuppressWarnings("unused")
public interface InflatableItemListener extends ItemListener {
    InflatableAdapter getInflatableAdapter();
    InflatableView getInflatableView();
}
