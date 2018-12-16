package com.mtri.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mtri.abstracts.Fragmentable;

import java.util.List;

public class FragmentableAdapter extends FragmentPagerAdapter{
    private List<Fragmentable> fragmentables;

    public FragmentableAdapter(FragmentManager manager, List<Fragmentable> fragmentables){
        super(manager);
        this.fragmentables = fragmentables;
    }

    @Override
    public Fragmentable getItem(int position){
        return fragmentables.get(position);
    }

    @Override
    public int getCount(){
        return fragmentables.size();
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getItemId();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getItem(position).getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        if(object instanceof Fragmentable){
            if(fragmentables.contains(object)){
                return fragmentables.indexOf(object);
            }
        }
        return POSITION_NONE;
    }
}
