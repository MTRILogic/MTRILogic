package com.mtrilogic.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.OnDialogDoneListener;

@SuppressWarnings("unused")
public abstract class Dialogable extends Dialog implements DialogInterface.OnClickListener{

    protected OnDialogDoneListener listener;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Dialogable(@NonNull Context context, @NonNull OnDialogDoneListener listener){
        super(context);
        this.listener = listener;
    }
}
