package com.mtrilogic.interfaces;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings("unused")
public interface ExpandableAdapterListener extends OnItemLongClickListener{
    ExpandableAdapter getExpandableAdapter();
    ExpandableView getExpandableView();
}
