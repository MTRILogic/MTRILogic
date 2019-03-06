package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Inflatable;

public interface InflatableListener extends OnMakeToastListener{
    Inflatable getInflatableItem(int viewType);
}
