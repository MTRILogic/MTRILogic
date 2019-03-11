package com.mtrilogic.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","WeakerAccess"})
public class FragmentableStateAdapter extends FragmentStatePagerAdapter{
    private static final String TAG = "FragmentStateAdaptTAGY";
    private static final String PAGINABLES = "paginables";
    private static final int NO_ITEMS = -1;
    private FragmentableListener listener;
    private List<Fragmentable> fragmentableList;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public FragmentableStateAdapter(FragmentManager manager, FragmentableListener listener){
        this(manager,listener,new ArrayList<Fragmentable>());
    }

    public FragmentableStateAdapter(FragmentManager manager, FragmentableListener listener, List<Fragmentable> fragmentableList){
        super(manager);
        this.listener = listener;
        this.fragmentableList = fragmentableList;
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public Fragmentable[] getFragmentables(){
        return fragmentableList.toArray(new Fragmentable[getCount()]);
    }

    public List<Fragmentable> getFragmentableList(){
        return fragmentableList;
    }

    public void setFragmentableList(List<Fragmentable> fragmentableList){
        this.fragmentableList = fragmentableList;
    }

    public boolean addFragmentable(Fragmentable fragmentable){
        return fragmentableList.add(fragmentable);
    }

    public Fragmentable getFragmentable(int position){
        return isValidPosition(position) ? getItem(position) : null;
    }

    public Fragmentable setFragmentable(int position, Fragmentable fragmentable){
        return isValidPosition(position) ? fragmentableList.set(position, fragmentable) : null;
    }

    public boolean removeFragmentable(int position){
        return isValidPosition(position) && fragmentableList.remove(getItem(position));
    }

    public void clearFragmentableList(){
        fragmentableList.clear();
    }

    public void restorePaginableInstance(Bundle instance){
        if(instance != null){
            Paginable[] paginables = (Paginable[])instance.getParcelableArray(PAGINABLES);
            if(paginables != null){
                for(Paginable paginable : paginables){
                    Fragmentable fragmentable = listener.getFragmentableInstance(paginable);
                    if(fragmentableList.add(fragmentable)){
                        Log.d(TAG, "restorePaginableInstance: Fragment added");
                    }
                }
            }
        }
    }

    public void savePaginableInstance(Bundle instance){
        int size = getCount();
        if(size > 0){
            Paginable[] paginables = new Paginable[size];
            for(int i = 0; i < size; i++){
                paginables[i] = getItem(i).getPaginable();
            }
            instance.putParcelableArray(PAGINABLES,paginables);
        }
    }

    //++++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public Fragmentable getItem(int position){
        return fragmentableList.get(position);
        // Nota: you should not use this method to get a fragmentable,
        // instead you should use getFragmentable(int) method
    }

    @Override
    public int getCount(){
        return fragmentableList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getItem(position).getPaginable().getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        if(object instanceof Fragmentable){
            if(fragmentableList.contains(object)){
                return fragmentableList.indexOf(object);
            }
        }
        return POSITION_NONE;
    }

    //++++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > NO_ITEMS && position < getCount();
    }
}
