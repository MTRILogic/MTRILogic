package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.interfaces.ItemListener;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Fragmentable<P extends Paginable, L extends FragmentListener> extends Fragment implements ItemListener {
    protected static final String PAGINABLE = "paginable";

    protected int position;
    protected L listener;
    protected P page;

    // ================< PUBLIC STATIC METHODS >====================================================

    @NonNull
    public static Fragmentable<?, ?> getInstance(@NonNull Paginable paginable, @NonNull Fragmentable<?, ?> fragmentable){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        fragmentable.setArguments(args);
        return fragmentable;
    }

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Fragmentable(@NonNull Paginable paginable){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        setArguments(args);
    }

    public Fragmentable(){
        super();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final Paginable getPaginable(){
        return page;
    }

    // ================< PROTECTED METHODS >========================================================

    public void onNewPosition(int position){
        this.position = position;
    }

    protected final void autoDelete(){
        if (listener != null && page != null){
            FragmentableAdapter adapter = listener.getFragmentableAdapter();
            if (adapter != null && adapter.getListable().getList().remove(page)){
                adapter.notifyDataSetChanged();
            }
        }
    }

    protected L getListenerFromContext(@NonNull Context context){
        return null;
    }

    protected void onViewPageCreated(@NonNull View view, @NonNull Context context, @NonNull P page, Bundle savedInstance){

    }

    protected View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent){
        return null;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener){
            listener = getListenerFromContext(context);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            page = savedInstanceState.getParcelable(PAGINABLE);
        }else {
            Bundle args = getArguments();
            if (args != null){
                page = args.getParcelable(PAGINABLE);
            }
        }
        if (page != null){
            String tag = getTag();
            if (tag != null && !tag.equals(page.getTagName())){
                page.setTagName(tag);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (listener != null && page != null){
            int position = listener.getFragmentableAdapter().getListable().getList().indexOf(page);
            onNewPosition(position);
        }
        if (page != null){
            Context context = view.getContext();
            onViewPageCreated(view, context, page, savedInstanceState);
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public boolean onItemTouch(@NonNull View view, @NonNull MotionEvent event, @NonNull Modelable modelable, int position) {
        return false;
    }

    @Override
    public boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position) {
        return false;
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position) {

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
}
