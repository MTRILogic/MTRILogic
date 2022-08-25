package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings({"unused"})
public abstract class Fragmentable<P extends Paginable> extends Fragment implements OnMakeToastListener {
    private static final String PAGINABLE = "paginable", POSITION = "position", INDEX = "index", TOP = "top";

    protected FragmentableListener listener;
    protected int position;
    protected P page;

    /*==============================================================================================
    PROTECTED ABSTRACT METHODS
    ==============================================================================================*/

    @NonNull
    protected abstract View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /*==============================================================================================
    PUBLIC STATIC METHOD
    ==============================================================================================*/

    @NonNull
    public static Fragmentable<? extends Paginable> getInstance(@NonNull Fragmentable<? extends Paginable> fragmentable, @NonNull Paginable paginable, int position){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        args.putInt(POSITION, position);
        fragmentable.setArguments(args);
        return fragmentable;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentableListener){
            listener = (FragmentableListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            page = savedInstanceState.getParcelable(PAGINABLE);
            position = savedInstanceState.getInt(POSITION);
        }else {
            Bundle args = getArguments();
            if (args != null){
                page = args.getParcelable(PAGINABLE);
                position = args.getInt(POSITION);
            }
        }
        if (page != null && listener != null){
            String tagName = page.getTagName();
            String tag = getTag();
            if (tag != null && !tag.equals(tagName)){
                page.setTagName(tag);
                listener.onNewTagName(tagName, tag);
            }
        }
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (page != null){
            return onCreateViewFragment(inflater, container, savedInstanceState);
        }
        return inflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(PAGINABLE, page);
        outState.putInt(POSITION, position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public final void onMakeToast(String line) {
        makeToast(line);
    }

    @Override
    public final void onMakeLog(String line) {
        makeLog(line);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void setPage(P page) {
        this.page = page;
    }

    @NonNull
    public Paginable getPaginable(){
        return page;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void restoreTopIndex(@NonNull ListView lvwItems){
        Bundle args = getArguments();
        if (args != null) {
            int index = args.getInt(INDEX, Base.INVALID_POSITION);
            if (index != Base.INVALID_POSITION) {
                int top = args.getInt(TOP);
                lvwItems.post(() -> lvwItems.setSelectionFromTop(index, top));
            }
        }
    }

    protected void saveTopIndex(@NonNull ListView lvwItems){
        Bundle args = getArguments();
        if (args != null) {
            int index = lvwItems.getFirstVisiblePosition();
            View view = lvwItems.getChildAt(0);
            int top = view != null ? view.getTop() - lvwItems.getPaddingTop() : 0;
            args.putInt(INDEX, index);
            args.putInt(TOP, top);
        }
    }

    protected void autoDelete(){
        if (listener != null && page != null){
            if (listener instanceof FragmentableItemListener){
                FragmentableItemListener itemListener = (FragmentableItemListener) listener;
                if (itemListener.getPaginableListable().delete(page)){
                    itemListener.getFragmentableAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    protected void makeToast(String line){
        if (listener != null){
            listener.onMakeToast(line);
        }
    }

    protected void makeLog(String line){
        if (listener != null){
            listener.onMakeLog(line);
        }
    }
}