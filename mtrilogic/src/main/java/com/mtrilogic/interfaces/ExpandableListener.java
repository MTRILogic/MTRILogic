package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.ExpandableGroup;

public interface ExpandableListener extends OnMakeToastListener{
    ExpandableGroup getExpandableGroup(int viewType);
    ExpandableChild getExpandableChild(int viewType);
}
