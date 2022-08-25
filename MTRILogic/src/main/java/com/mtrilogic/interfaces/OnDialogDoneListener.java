package com.mtrilogic.interfaces;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public interface OnDialogDoneListener extends OnMakeToastListener{
    void onDialogDone(@NonNull Bundle bundle);
}
