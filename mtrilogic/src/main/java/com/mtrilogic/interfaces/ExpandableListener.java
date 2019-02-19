package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Expandable;

public interface ExpandableListener{
    Expandable getExpandableItem(int viewType, boolean isChild);
}
