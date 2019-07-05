package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Inflatable;

public interface InflatableListener extends OnMakeToastListener{
    Inflatable getInflatable(int viewType);
}
