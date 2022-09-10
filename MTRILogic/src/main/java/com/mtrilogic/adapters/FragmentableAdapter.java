package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;

@SuppressWarnings({"unused","deprecation"})
public final class FragmentableAdapter extends FragmentPagerAdapter {
    private final FragmentableAdapterListener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public FragmentableAdapter(@NonNull FragmentManager manager, @NonNull FragmentableAdapterListener listener){
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragment getItem(int position){
        return listener.getFragment(getPage(position), position);
    }

    @Override
    public int getCount(){
        return getPageListable().getCount();
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        Fragmentable fragmentable = (Fragmentable) object;
        Page page = fragmentable.getPage();
        Listable<Page> paginableListable = getPageListable();
        if (paginableListable.contains(page)){
            int position = paginableListable.getPosition(page);
            if (fragmentable.getPosition() != position){
                fragmentable.setPosition(position);
                listener.onPositionChanged(position);
                return position;
            }
            return POSITION_UNCHANGED;
        }
        if (getCount() == 0){
            listener.onPositionChanged(Base.INVALID_POSITION);
        }
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getPage(position).getPageTitle();
    }

    @Override
    public long getItemId(int position){
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
