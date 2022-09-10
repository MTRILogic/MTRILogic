package com.mtrilogic.interfaces;

import com.mtrilogic.abstracts.Page;

@SuppressWarnings("unused")
public interface Fragmentable extends OnMakeToastListener {
    void setPosition(int position);
    int getPosition();
    Page getPage();
}
