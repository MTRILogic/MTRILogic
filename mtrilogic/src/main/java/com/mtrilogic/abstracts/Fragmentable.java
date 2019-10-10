package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings("unused")
public abstract class Fragmentable<P extends Paginable> extends Fragment{
    protected static final String PAGE = "page";
    private FragmentableAdapterListener listener;
    private P page;
    private int position;

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentableAdapterListener){
            listener = (FragmentableAdapterListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            page = args.getParcelable(PAGE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = listener.getFragmentableAdapter().getPaginablePosition(page);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public FragmentableAdapterListener getListener(){
        return listener;
    }

    public P getPage(){
        return page;
    }

    public int getPosition() {
        return position;
    }
}
