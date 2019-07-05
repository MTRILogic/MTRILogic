package com.mtrilogic.abstracts;

import androidx.fragment.app.Fragment;

@SuppressWarnings("unused")
public abstract class Fragmentable extends Fragment{
    protected static final String PAGE = "page";

// ++++++++++++++++| PUBLIC ABSTRACT METHODS |+++++++++++++++++++++++++++++++++

    public abstract Paginable getPaginable();

    public abstract int getPosition();
}
