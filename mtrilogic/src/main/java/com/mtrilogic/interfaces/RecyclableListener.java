package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Recyclable;

public interface RecyclableListener extends OnMakeToastListener{
    Recyclable getRecyclableItem(int viewType);
}
