package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnChangePositionListener {
    Fragmentable getFragmentable(@NonNull Paginable paginable);
}
