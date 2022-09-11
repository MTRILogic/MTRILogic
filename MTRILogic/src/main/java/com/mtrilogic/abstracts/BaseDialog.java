package com.mtrilogic.abstracts;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Dialogable;
import com.mtrilogic.interfaces.OnDialogDoneListener;

@SuppressWarnings("unused")
public abstract class BaseDialog<P extends Page> extends Dialog implements Dialogable {
    private static final String PAGE = "page";

    protected final OnDialogDoneListener<P> listener;

    protected Listable<Page> pageListable;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public BaseDialog(@NonNull Context context, @NonNull OnDialogDoneListener<P> listener) {
        super(context);
        this.listener = listener;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull OnDialogDoneListener<P> listener) {
        super(context, cancelable, cancelListener);
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    public void onMakeToast(String line) {
        makeToast(line);
    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    protected final void makeToast(String line){
        listener.onMakeToast(line);
    }
}
