package com.mtrilogic.abstracts;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.interfaces.Dialogable;
import com.mtrilogic.interfaces.OnDialogDoneListener;

@SuppressWarnings("unused")
public abstract class BaseDialog<P extends Page> extends Dialog implements Dialogable {
    private static final String PAGE = "page";

    protected final OnDialogDoneListener<P> listener;
    protected P page;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public BaseDialog(@NonNull Context context, @NonNull P page, @NonNull OnDialogDoneListener<P> listener) {
        super(context);
        this.page = page;
        this.listener = listener;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull P page, @NonNull OnDialogDoneListener<P> listener) {
        super(context, cancelable, cancelListener);
        this.page = page;
        this.listener = listener;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            page = savedInstanceState.getParcelable(PAGE);
        }
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        Bundle outState = super.onSaveInstanceState();
        outState.putParcelable(PAGE, page);
        return outState;
    }

    @Override
    public void onMakeToast(String line) {
        makeToast(line);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void makeToast(String line){
        listener.onMakeToast(line);
    }
}
