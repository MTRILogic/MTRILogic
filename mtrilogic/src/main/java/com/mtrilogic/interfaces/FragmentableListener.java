package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;

public interface FragmentableListener extends OnMakeToastListener{
    Fragmentable getFragmentableInstance(Paginable paginable);
}
