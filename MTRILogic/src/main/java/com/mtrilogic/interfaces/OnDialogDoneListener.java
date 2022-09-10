package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Page;

@SuppressWarnings("unused")
public interface OnDialogDoneListener<P extends Page> extends OnMakeToastListener{
    void onDialogDone(@NonNull P page);
}
