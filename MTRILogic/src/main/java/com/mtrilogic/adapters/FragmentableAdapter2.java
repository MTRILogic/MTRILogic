package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings("unused")
public final class FragmentableAdapter2 extends FragmentStateAdapter {

    private final FragmentableAdapterListener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public FragmentableAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, @NonNull FragmentableAdapterListener listener) {
        super(fragmentManager, lifecycle);
        this.listener = listener;
    }

    public FragmentableAdapter2(@NonNull FragmentActivity fragmentActivity, @NonNull FragmentableAdapterListener listener) {
        super(fragmentActivity);
        this.listener = listener;
    }

    public FragmentableAdapter2(@NonNull Fragment fragment, @NonNull FragmentableAdapterListener listener) {
        super(fragment);
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Page page = getPage(position);
        Fragmentable fragmentable = listener.getFragmentable(page.getViewType());
        fragmentable.bindPage(page, position);
        return (Fragment) fragmentable;
    }

    @Override
    public int getItemCount() {
        return getPageListable().getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return getPage(position).getViewType();
    }

    @Override
    public long getItemId(int position) {
        return getPage(position).getItemId();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private Listable<Page> getPageListable(){
        return listener.getPageListable();
    }

    private Page getPage(int position){
        return getPageListable().get(position);
    }
}
