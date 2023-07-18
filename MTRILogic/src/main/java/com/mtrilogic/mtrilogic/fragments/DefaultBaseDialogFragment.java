package com.mtrilogic.mtrilogic.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.BaseDialogFragment;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.pages.DefaultPage;

public class DefaultBaseDialogFragment extends BaseDialogFragment<DefaultPage> {

    private TextView lblTitle;

    /*==============================================================================================
    PUBLIC OVERRIDE METHOD
    ==============================================================================================*/

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        lblTitle = view.findViewById(R.id.lblTitle);
        onNewPosition();
        return view;
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    protected void onNewPosition() {
        lblTitle.setText(getString(R.string.default_fragment, page.getItemId(), position));
    }
}
