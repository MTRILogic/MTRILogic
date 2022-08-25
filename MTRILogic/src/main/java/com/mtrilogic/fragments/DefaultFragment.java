package com.mtrilogic.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.pages.DefaultPage;

@SuppressWarnings("unused")
public class DefaultFragment extends Fragmentable<DefaultPage> {
    private TextView lblTitle;

    @NonNull
    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        lblTitle = view.findViewById(R.id.lblTitle);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    @Override
    public void setPosition(int position) {
        super.setPosition(position);
        setData();
    }

    private void setData(){
        if (page != null) {
            lblTitle.setText(getString(R.string.default_fragment, page.getItemId(), position));
        }
    }
}
