package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings({"unused","EmptyMethod"})
public abstract class BaseFragment<P extends Page> extends Fragment implements Fragmentable {
    private static final String PAGE = "page", POSITION = "position";

    protected FragmentableListener listener;
    protected int position;
    protected P page;

    /*==============================================================================================
    PUBLIC STATIC METHOD
    ==============================================================================================*/

    @NonNull
    public static Fragment getInstance(@NonNull Fragment fragment, @NonNull Page page, int position){
        Bundle args = new Bundle();
        args.putParcelable(PAGE, page);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentableItemListener){
            listener = (FragmentableItemListener) context;
        }
        if (context instanceof FragmentableListener){
            listener = (FragmentableListener) context;
        }else {
            throw new RuntimeException("BaseFragment > No FragmentableListener attached");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            page = savedInstanceState.getParcelable(PAGE);
            position = savedInstanceState.getInt(POSITION);
        }else {
            Bundle args = getArguments();
            if (args != null){
                page = args.getParcelable(PAGE);
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(PAGE, page);
        outState.putInt(POSITION, position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public final Page getPage() {
        return page;
    }

    @Override
    public final void setPosition(int position) {
        this.position = position;
        onNewPosition();
    }

    @Override
    public final int getPosition() {
        return position;
    }

    @Override
    public void onMakeToast(String line) {
        makeToast(line);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public final void setPage(P page) {
        this.page = page;
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    /**
     * Utility method to listen for position update.
     */
    protected void onNewPosition(){

    }

    /**
     * Schedule this fragment to be deleted on adapter update.
     * <b>WARNING!!!</b>: use this method only if this fragment is in page mode, i.e. with
     * FragmentableItemListener implemented.
     * @return true if page was deleted from list.
     */
    protected final boolean autoDelete(){
        return getItemListener().getPageListable().delete(page);
    }

    /**
     *  Notify the adapter that the listable has changed.
     * <b>WARNING!!!</b>: use this method only if this fragment is in page mode, i.e. with
     * FragmentableItemListener implemented.
     */
    protected final void notifyChanged(){
        getItemListener().getFragmentableAdapter().notifyDataSetChanged();
    }

    protected final void makeToast(String line){
        if (listener != null){
            listener.onMakeToast(line);
        }
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private FragmentableItemListener getItemListener(){
        return (FragmentableItemListener) listener;
    }
}
