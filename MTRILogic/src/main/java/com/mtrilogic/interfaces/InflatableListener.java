package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

@SuppressWarnings({"unused", "EmptyMethod", "SameReturnValue"})
public interface InflatableListener extends OnMakeToastListener {

    boolean onItemLongClick(@NonNull View itemView, @NonNull Model model, int position);

    void onItemClick(@NonNull View itemView, @NonNull Model model, int position);
}
