package com.mtrilogic.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;

import java.util.ArrayList;
import java.util.List;

public class FragmentableAdapter extends FragmentPagerAdapter{
    private List<Fragmentable> fragments;

    @SuppressWarnings("unused")
    public FragmentableAdapter(FragmentManager manager){
        this(manager,new ArrayList<Fragmentable>());
    }

    public FragmentableAdapter(FragmentManager manager, List<Fragmentable> fragments){
        super(manager);
        this.fragments = fragments;
    }

    @Override
    public Fragmentable getItem(int position){
        return fragments.get(position);
    }

    @Override
    public int getCount(){
        return fragments.size();
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
            if(fragments.contains(object)){
                return fragments.indexOf(object);
            }
        }
        return POSITION_NONE;
    }

    @SuppressWarnings("unused")
    public String[] getTags(){
        int size = getCount();
        String[] tags = new String[size];
        for(int i = 0; i < size; i++){
            tags[i] = getItem(i).getTag();
        }
        return tags;
    }

    @SuppressWarnings("unused")
    public String[] getTitles(){
        int size = getCount();
        String[] titles = new String[size];
        for(int i = 0; i < size; i++){
            titles[i] = (String)getPageTitle(i);
        }
        return titles;
    }
}
