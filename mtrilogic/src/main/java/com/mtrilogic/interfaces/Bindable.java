package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Modelable;

public interface Bindable{
    void onBindHolder(Modelable modelable);
    int getLayoutResource();
}
