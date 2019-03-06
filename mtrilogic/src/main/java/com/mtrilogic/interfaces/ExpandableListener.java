package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Expandable;

public interface ExpandableListener extends OnMakeToastListener{
    Expandable getExpandableItem(int viewType, boolean isChild);
}
