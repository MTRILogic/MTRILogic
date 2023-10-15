package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings({"unused","deprecation"})
public final class FragmentableStateAdapter extends FragmentStatePagerAdapter {

    private final FragmentableAdapterListener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public FragmentableStateAdapter(@NonNull FragmentManager manager, @NonNull FragmentableAdapterListener listener) {
        super(manager);
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Page page = getPage(position);
        Fragmentable fragmentable = listener.getFragmentable(page.getViewType());
        fragmentable.bindPage(page, position);
        return (Fragment) fragmentable;
    }

    @Override
    public int getCount() {
        return getPageListable().getCount();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;
        int position = getPageListable().getPosition(fragmentable.getPage());
        if (fragmentable.getPosition() != position){
            listener.onPositionChanged(fragmentable, position);
            if (position > Base.INVALID_POSITION){
                fragmentable.updatePosition(position);
                return position;
            }
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPage(position).getPageTitle();
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
