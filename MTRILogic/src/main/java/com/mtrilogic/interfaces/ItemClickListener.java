package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface ItemClickListener {
    boolean onItemLongClick(@NonNull View itemView, @NonNull Modelable modelable, int position);

    void onItemClick(@NonNull View itemView, @NonNull Modelable modelable, int position);
}
