package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.interfaces.FragmentableListener;

@SuppressWarnings({"unused", "EmptyMethod"})
public abstract class BaseDialogFragment<P extends Page> extends DialogFragment implements Fragmentable {

    private static final String PAGINABLE = "paginable", POSITION = "position";

    protected FragmentableListener listener;
    protected int position;
    protected P page;

    /*==============================================================================================
    PUBLIC STATIC METHOD
    ==============================================================================================*/

    @NonNull
    public static Fragment getInstance(@NonNull Fragment fragment, @NonNull Page page, int position){
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, page);
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
        if (context instanceof FragmentableListener){
            listener = (FragmentableListener) context;
        }else {
            throw new RuntimeException("BaseDialogFragment: No FragmentableListener attached");
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
    public final void setPosition(int position) {
        this.position = position;
        onNewPosition();
    }

    @Override
    public final int getPosition() {
        return position;
    }

    @Override
    public final Page getPage() {
        return page;
    }

    @Override
    public final void onMakeToast(String line) {
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

    /**
     * Convenient method to get the FragmentableItemListener from the FrangmentableListener
     * <b>WARNING!!!</b>: use this method only if this fragment is in page mode, i.e. with
     * FragmentableItemListener implemented.
     */
    protected FragmentableItemListener getItemListener(){
        return (FragmentableItemListener) listener;
    }

    /**
     * Utility method to update fragment position
     */
    protected void onNewPosition(){

    }

    protected final void makeToast(String line){
        if (listener != null){
            listener.onMakeToast(line);
        }
    }
}
