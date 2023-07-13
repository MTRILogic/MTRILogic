package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

public interface OnNewModelListener<M extends Model> {

    @NonNull
    M onNewModel(long idx);
}
