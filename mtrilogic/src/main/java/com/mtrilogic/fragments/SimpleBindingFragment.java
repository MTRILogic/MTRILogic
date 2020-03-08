package com.mtrilogic.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.BindingFragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.mtrilogic.databinding.FragmentSimpleBinding;
import com.mtrilogic.pages.SimplePage;

@SuppressWarnings("unused")
public class SimpleBindingFragment extends BindingFragmentable<SimplePage, FragmentableAdapterListener, FragmentSimpleBinding> {

    @Override
    protected FragmentableAdapterListener getListenerFromContext(@NonNull Context context) {
        return (FragmentableAdapterListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSimpleBinding.inflate(inflater, container, false);
        FrameLayout maingContainer = binding.mainContainer;
        TextView lblText = binding.lblText;
        if (page != null) {
            maingContainer.setBackgroundColor(page.getBackColor());
            lblText.setTextColor(page.getColor());
            lblText.setText(page.getText());
        }
        return binding.getRoot();
    }

    @Override
    public void onNewPosition(int position) {

    }
}
