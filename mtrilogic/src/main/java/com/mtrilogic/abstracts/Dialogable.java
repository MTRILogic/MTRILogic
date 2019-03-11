package com.mtrilogic.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.mtrilogic.interfaces.OnDialogDoneListener;

@SuppressWarnings("unused")
public abstract class Dialogable extends Dialog implements DialogInterface.OnClickListener{
    private OnDialogDoneListener listener;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public Dialogable(Context context, OnDialogDoneListener listener){
        super(context);
        this.listener = listener;
    }

    // +++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++

    protected OnDialogDoneListener getListener(){
        return listener;
    }
}
