package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnMakeToastListener {
    Fragmentable getFragmentable(@NonNull Paginable paginable);
    void onChangePosition(int position);
}
