package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface ItemActionListener extends OnMakeToastListener {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position);
    boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position);
}
