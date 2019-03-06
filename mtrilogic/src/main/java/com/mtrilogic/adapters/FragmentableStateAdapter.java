package com.mtrilogic.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.interfaces.FragmentableInstanceListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class FragmentableStateAdapter extends FragmentStatePagerAdapter{
    //private static final String TAG = "FragmentStateAdapterTAG";
    private FragmentableInstanceListener listener;
    private List<Fragmentable> fragmentables;

    @SuppressWarnings("unused")
    public FragmentableStateAdapter(FragmentManager manager, FragmentableInstanceListener listener){
        this(manager,listener,new ArrayList<Fragmentable>());
    }

    @SuppressWarnings("WeakerAccess")
    public FragmentableStateAdapter(FragmentManager manager, FragmentableInstanceListener listener, List<Fragmentable> fragmentables){
        super(manager);
        this.listener = listener;
        this.fragmentables = fragmentables;
    }

    @SuppressWarnings("unused")
    public List<Fragmentable> getFragmentables(){
        return fragmentables;
    }

    @SuppressWarnings("unused")
    public void setFragmentables(List<Fragmentable> fragmentables){
        this.fragmentables = fragmentables;
    }

    @SuppressWarnings("unused")
    public boolean addFragmentable(Fragmentable fragmentable){
        return fragmentables.add(fragmentable);
    }

    @SuppressWarnings("unused")
    public Fragmentable setFragmentable(int position, Fragmentable fragmentable){
        return fragmentables.set(position,fragmentable);
    }

    @SuppressWarnings("unused")
    public boolean removeFragmentable(int position){
        return fragmentables.remove(fragmentables.get(position));
    }

    @SuppressWarnings("unused")
    public void clearFragmentables(){
        fragmentables.clear();
    }

    @Override
    public Fragmentable getItem(int position){
        return fragmentables.get(position);
    }

    @Override
    public int getCount(){
        return fragmentables.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getItem(position).getPaginable().getPageTitle();
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

    @SuppressWarnings("unused")
    public long restorePaginableInstance(Bundle instance){
        long idx = 0;
        if(instance != null){
            Paginable[] paginables = (Paginable[])instance.getParcelableArray("paginables");
            if(paginables != null){
                for(Paginable paginable : paginables){
                    Fragmentable fragmentable = listener.getFragmentableInstance(paginable);
                    if(fragmentables.add(fragmentable)){
                        idx++;
                    }
                }
            }
        }
        return idx;
    }

    @SuppressWarnings("unused")
    public void savePaginableInstance(Bundle instance){
        int size = getCount();
        if(size > 0){
            Paginable[] paginables = new Paginable[size];
            for(int i = 0; i < size; i++){
                paginables[i] = getItem(i).getPaginable();
            }
            instance.putParcelableArray("paginables",paginables);
        }
    }
}
