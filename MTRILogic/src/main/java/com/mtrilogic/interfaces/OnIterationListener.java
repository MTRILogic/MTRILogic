package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface OnIterationListener<M extends Modelable> {
    void onIteration(@NonNull M item);
}
