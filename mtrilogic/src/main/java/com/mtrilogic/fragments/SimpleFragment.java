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

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.pages.SimplePage;

@SuppressWarnings("unused")
public class SimpleFragment extends Fragmentable<SimplePage, FragmentableAdapterListener> {

    @Override
    protected FragmentableAdapterListener getListenerFromContext(@NonNull Context context) {
        return (FragmentableAdapterListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        FrameLayout maingContainer = view.findViewById(R.id.mainContainer);
        TextView lblText = view.findViewById(R.id.lblText);
        if (page != null) {
            maingContainer.setBackgroundColor(page.getBackColor());
            lblText.setTextColor(page.getColor());
            lblText.setText(page.getText());
        }
        return view;
    }

    @Override
    public void onNewPosition(int position) {

    }
}
