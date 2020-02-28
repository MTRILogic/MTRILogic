package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnMakeToastListener{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    Fragmentable getFragmentable(Paginable paginable, int position);
    void onPositionChanged(int position);
}
