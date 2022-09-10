package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Page;

@SuppressWarnings({"unused", "EmptyMethod", "SameReturnValue"})
public interface PaginableListener extends OnMakeToastListener {
    boolean onPaginableLongClick(@NonNull View itemView, @NonNull Page page, int position);

    void onPaginableClick(@NonNull View itemView, @NonNull Page page, int position);
}
