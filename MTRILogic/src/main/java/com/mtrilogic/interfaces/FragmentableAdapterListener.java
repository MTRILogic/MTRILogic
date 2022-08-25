package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface FragmentableAdapterListener extends OnMakeToastListener{
    @NonNull
    Fragmentable<? extends Paginable> getFragmentable(@NonNull Paginable paginable, int position);
    @NonNull
    Listable<Paginable> getPaginableListable();
    void onPositionChanged(int position);
}
