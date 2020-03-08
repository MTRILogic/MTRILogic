package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface ItemActionListener extends OnMakeToastListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position);
    void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position);
}
