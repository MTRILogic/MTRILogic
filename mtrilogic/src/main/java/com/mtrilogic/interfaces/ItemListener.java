package com.mtrilogic.interfaces;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface ItemListener extends OnMakeToastListener {
    boolean onItemTouch(@NonNull View view, @NonNull MotionEvent event, @NonNull Modelable modelable, int position);
    boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position);
    void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position);
}
