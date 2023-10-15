package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Page;

@SuppressWarnings("unused")
public interface Fragmentable extends OnMakeToastListener {

    void bindPage(@NonNull Page page, int position);

    void updatePosition(int position);

    int getPosition();

    Page getPage();
}
