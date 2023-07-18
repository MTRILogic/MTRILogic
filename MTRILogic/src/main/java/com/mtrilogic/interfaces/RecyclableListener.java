package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

@SuppressWarnings({"unused", "EmptyMethod", "SameReturnValue"})
public interface RecyclableListener extends OnMakeToastListener {

    boolean onRecyclableLongClick(@NonNull View itemView, @NonNull Model model, int position);

    void onRecyclableClick(@NonNull View itemView, @NonNull Model model, int position);
}
