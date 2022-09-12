package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

@SuppressWarnings({"unused", "EmptyMethod"})
public interface OnTaskCompleteListener<M extends Model> extends OnMakeToastListener{
    void onTaskComplete(@NonNull M model, int result);
    void onTaskError(@NonNull String msg);
}
