package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.OnMakeToastListener;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Fragmentable<P extends Paginable> extends Fragment implements OnMakeToastListener {

    protected static final String PAGINABLE = "paginable";
    protected FragmentableAdapterListener listener;
    protected Bundle args;
    protected P page;

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    public abstract void onNewPosition(int position);

    // ================< PUBLIC STATIC METHODS >====================================================

    @NonNull
    public static Fragmentable getInstance(@NonNull Paginable paginable,
                                           @NonNull Fragmentable fragmentable){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        fragmentable.setArguments(args);
        return fragmentable;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

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
        args = getArguments();
        if (args != null){
            page = args.getParcelable(PAGINABLE);
            if (page != null){
                String tag = getTag();
                if (tag != null){
                    page.setTagName(tag);
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (listener != null) {
            int position = listener.getFragmentableAdapter().getPaginablePosition(page);
            onNewPosition(position);
        }
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

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (listener != null){
            listener.uncaughtException(t, e);
        }
    }

    // ================< PUBLIC METHODS >===========================================================

    public final Paginable getPaginable(){
        return page;
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        FragmentableAdapter adapter = listener.getFragmentableAdapter();
        if (adapter != null){
            if (adapter.removePaginable(page)){
                adapter.notifyDataSetChanged();
            }else {
                listener.onMakeLog("Fragmentable:autoDelete: failed to remove page");
            }
        }else {
            listener.onMakeLog("Fragmentable:autoDelete: Adapter is null");
        }
    }
}
