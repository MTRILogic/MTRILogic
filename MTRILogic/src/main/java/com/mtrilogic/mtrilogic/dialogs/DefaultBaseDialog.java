package com.mtrilogic.mtrilogic.dialogs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.BaseDialog;
import com.mtrilogic.interfaces.BaseDialogListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.models.DefaultModel;

@SuppressWarnings("unused")
public class DefaultBaseDialog extends BaseDialog<DefaultModel> {

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultBaseDialog(@NonNull Context context, @NonNull BaseDialogListener<DefaultModel> listener) {
        super(context, listener);
    }

    protected DefaultBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull BaseDialogListener<DefaultModel> listener) {
        super(context, cancelable, cancelListener, listener);
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_default);
    }
}
