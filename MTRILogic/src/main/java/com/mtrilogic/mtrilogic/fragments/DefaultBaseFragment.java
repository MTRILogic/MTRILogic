package com.mtrilogic.mtrilogic.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.BaseFragment;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.databinding.FragmentDefaultBinding;
import com.mtrilogic.mtrilogic.pages.DefaultPage;

@SuppressWarnings("unused")
public class DefaultBaseFragment extends BaseFragment<DefaultPage> {

    private FragmentDefaultBinding binding;

    /*==============================================================================================
    PUBLIC OVERRIDE METHOD
    ==============================================================================================*/

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDefaultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onNewPosition();
    }

    /*==============================================================================================
    PROTECTED OVERRIDE METHOD
    ==============================================================================================*/

    @Override
    protected void onNewPosition() {
        binding.lblTitle.setText(getString(R.string.default_fragment, page.getItemId(), position));
    }
}
