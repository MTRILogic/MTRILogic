package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface Bindable<M extends Modelable> extends OnMakeToastListener {
    M getModelFromModelable(Modelable modelable);
    void onBindItemView();
    void onBindModel();
}
