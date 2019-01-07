package com.mtri.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.mtri.interfaces.OnDialogDoneListener;

public abstract class Dialogable extends Dialog implements DialogInterface.OnClickListener{
    protected OnDialogDoneListener listener;
    protected int classId;

    public Dialogable(Context context, OnDialogDoneListener listener, int classId){
        super(context);
        this.listener = listener;
        this.classId = classId;
    }
}
