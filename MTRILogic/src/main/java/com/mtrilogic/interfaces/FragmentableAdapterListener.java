package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Listable;

@SuppressWarnings({"unused", "EmptyMethod"})
public interface FragmentableAdapterListener extends OnMakeToastListener{
    @NonNull
    Fragment getFragment(@NonNull Page page, int position);

    @NonNull
    Listable<Page> getPageListable();

    void onPositionChanged(int position);
}
