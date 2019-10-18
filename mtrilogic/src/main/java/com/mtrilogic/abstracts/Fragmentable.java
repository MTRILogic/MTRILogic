package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.OnMakeToastListener;

@SuppressWarnings("unused")
public abstract class Fragmentable<P extends Paginable> extends Fragment implements OnMakeToastListener {
    private static final String PAGINABLE = "paginable";
    private FragmentableAdapterListener listener;
    private P page;
    private int position;

    protected abstract View onCreateViewFragment(@NonNull LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState,
                                                 P page, int position);
    protected abstract void onNewPosition(int position);

// ++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static Fragmentable getInstance(Paginable paginable, Fragmentable fragmentable){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        fragmentable.setArguments(args);
        return fragmentable;
    }

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
            page = args.getParcelable(PAGINABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (page != null){
            position = listener.getFragmentableAdapter().getPaginablePosition(page);
            return onCreateViewFragment(inflater, container, savedInstanceState, page, position);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public void onMakeToast(String line){
        if (listener != null) {
            listener.onMakeToast(line);
        }
    }

// ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void deletePaginable(Paginable paginable){
        if (listener != null){
            FragmentableAdapter adapter = listener.getFragmentableAdapter();
            if (adapter.removePaginable(paginable)){
                adapter.notifyDataSetChanged();
            }
        }
    }

    public FragmentableAdapterListener getListener(){
        return listener;
    }

    public P getPage(){
        return page;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        onNewPosition(position);
    }
}
