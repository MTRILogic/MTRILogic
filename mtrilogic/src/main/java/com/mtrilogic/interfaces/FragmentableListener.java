package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;

public interface FragmentableListener extends OnMakeToastListener{
    Fragmentable getFragmentable(Paginable paginable, int position);
    void onPositionChanged(int position);
}
