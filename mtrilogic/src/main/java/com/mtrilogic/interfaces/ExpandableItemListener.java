package com.mtrilogic.interfaces;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings("unused")
public interface ExpandableItemListener extends ItemListener {
    ExpandableAdapter getExpandableAdapter();
    ExpandableView getExpandableView();
}
