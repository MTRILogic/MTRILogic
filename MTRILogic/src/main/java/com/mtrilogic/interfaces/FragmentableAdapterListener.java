package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Listable;

@SuppressWarnings({"unused", "EmptyMethod"})
public interface FragmentableAdapterListener extends AdapterListener, OnMakeToastListener{

    void onPositionChanged(@NonNull Fragmentable fragmentable, int position);

    @NonNull
    Fragmentable getFragmentable(int viewType);

    @NonNull
    Listable<Page> getPageListable();
}
