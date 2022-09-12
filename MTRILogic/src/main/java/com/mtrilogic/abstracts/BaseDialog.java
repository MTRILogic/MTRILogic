package com.mtrilogic.abstracts;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.interfaces.Dialogable;
import com.mtrilogic.interfaces.OnTaskCompleteListener;

@SuppressWarnings("unused")
public abstract class BaseDialog<M extends Model> extends Dialog implements Dialogable {
    protected final OnTaskCompleteListener<M> listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public BaseDialog(@NonNull Context context, @NonNull OnTaskCompleteListener<M> listener) {
        super(context);
        this.listener = listener;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull OnTaskCompleteListener<M> listener) {
        super(context, cancelable, cancelListener);
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    public final void onMakeToast(String line) {
        makeToast(line);
    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    protected final void makeToast(String line){
        listener.onMakeToast(line);
    }
}
