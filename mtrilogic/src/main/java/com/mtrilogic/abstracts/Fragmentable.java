package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.interfaces.OnMakeToastListener;

import java.util.ArrayList;

@SuppressWarnings({"unused","WeakerAccess"})
public abstract class Fragmentable<P extends Paginable, L extends FragmentListener>
        extends Fragment implements OnMakeToastListener {
    protected static final String PAGINABLE = "paginable";

    protected L listener;
    protected P page;

    // ================< PUBLIC STATIC METHODS >====================================================

    @NonNull
    public static Fragmentable getInstance(@NonNull Paginable paginable,
                                           @NonNull Fragmentable fragmentable){
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

    public void onNewPosition(int position){}

    // ================< PROTECTED METHODS >========================================================

    protected L getListenerFromContext(@NonNull Context context){
        return null;
    }

    protected final void autoDelete(){
        if (listener != null && page != null){
            FragmentableAdapter adapter = listener.getFragmentableAdapter();
            if (adapter != null){
                Listable<Paginable> listable = adapter.getListable();
                if (listable != null){
                    ArrayList<Paginable> list = listable.getList();
                    if (list.remove(page)){
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
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
        Bundle args = getArguments();
        if (args != null){
            page = args.getParcelable(PAGINABLE);
            if (page != null){
                String tagName = page.getTagName();
                String tag = getTag();
                if (tag != null && !tag.equals(tagName)){
                    page.setTagName(tag);
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (listener != null && page != null){
            FragmentableAdapter adapter = listener.getFragmentableAdapter();
            if (adapter != null){
                Listable<Paginable> listable = adapter.getListable();
                if (listable != null){
                    int position = listable.getList().indexOf(page);
                    onNewPosition(position);
                }
            }
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
}
