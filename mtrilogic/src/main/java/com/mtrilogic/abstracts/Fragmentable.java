package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.OnMakeToastListener;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Fragmentable<P extends Paginable> extends Fragment implements OnMakeToastListener {
    private static final String PAGINABLE = "paginable", STATE = "state";
    protected abstract View onCreateViewFragment(@NonNull LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState);

    protected abstract void onNewPosition();
    protected FragmentableAdapterListener listener;
    protected FragmentableAdapter adapter;
    protected ViewPager viewPager;
    protected Context context;
    protected Bundle args;
    protected int position;
    protected P page;

// ++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static Fragmentable getInstance(Paginable paginable, Fragmentable fragmentable){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        args.putBundle(STATE, new Bundle());
        fragmentable.setArguments(args);
        return fragmentable;
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof FragmentableAdapterListener){
            listener = (FragmentableAdapterListener) context;
            adapter = listener.getFragmentableAdapter();
            viewPager = listener.getViewPager();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
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
            View view = onCreateViewFragment(inflater, container, savedInstanceState);
            onNewPosition();
            return view;
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

    @Override
    public void onMakeLog(String line) {
        if (listener != null){
            listener.onMakeLog(line);
        }
    }

    // ++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public Paginable getPaginable(){
        return page;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        onNewPosition();
        String s = super.getTag();
    }

// ++++++++++++++++| PROTECTED METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected void autoDelete(){
        if (adapter != null){
            if (adapter.removePaginable(page)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
