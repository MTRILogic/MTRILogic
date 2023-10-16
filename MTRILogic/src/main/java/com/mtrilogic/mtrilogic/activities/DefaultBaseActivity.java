package com.mtrilogic.mtrilogic.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.BaseActivity;
import com.mtrilogic.mtrilogic.databinding.ActivityDefaultBinding;

@SuppressWarnings("unused")
public class DefaultBaseActivity extends BaseActivity {

    /*==============================================================================================
    PROTECTED OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityDefaultBinding.inflate(getLayoutInflater()).getRoot());
    }
}
