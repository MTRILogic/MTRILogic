package com.mtrilogic.interfaces;

import android.os.Bundle;

@SuppressWarnings("unused")
public interface OnDialogDoneListener extends OnMakeToastListener{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    void onDialogDone(Bundle bundle);
}
