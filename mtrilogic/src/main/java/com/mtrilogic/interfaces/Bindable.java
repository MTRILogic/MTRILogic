package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface Bindable<M extends Modelable> extends OnMakeToastListener {
    M getModelFromModelable(@NonNull Modelable modelable);
    void onBindItemView();
    void onBindModel();
}
