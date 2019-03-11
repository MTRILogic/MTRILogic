package com.mtrilogic.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused","WeakerAccess"})
public class FragmentableAdapter extends FragmentPagerAdapter{
    private static final String TAG = "FragmentableAdapterTAGY";
    private static final String PAGINABLES = "paginables";
    private static final int NO_ITEMS = -1;
    private FragmentManager manager;
    private FragmentableListener listener;
    private List<Fragmentable> fragmentableList;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public FragmentableAdapter(FragmentManager manager, FragmentableListener listener){
        this(manager,listener,new ArrayList<Fragmentable>());
    }

    public FragmentableAdapter(FragmentManager manager, FragmentableListener listener, List<Fragmentable> fragmentableList){
        super(manager);
        this.manager = manager;
        this.listener = listener;
        this.fragmentableList = fragmentableList;
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public List<Fragmentable> getFragmentableList(){
        return fragmentableList;
    }

    public void setFragmentableList(List<Fragmentable> fragmentableList){
        this.fragmentableList = fragmentableList;
    }

    public boolean addFragmentable(Fragmentable fragmentable){
        return !fragmentableList.contains(fragmentable) && fragmentableList.add(fragmentable);
        // Nota: The fragmentable must have at least different id,
        // otherwise, you should use an external list
    }

    public Fragmentable setFragmentable(int position, Fragmentable fragmentable){
        return isValidPosition(position) ? fragmentableList.set(position, fragmentable) : null;
    }

    public Fragmentable getFragmentable(int position){
        return isValidPosition(position) ? getItem(position) : null;
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
                    Fragmentable fragmentable = (Fragmentable)manager.findFragmentByTag(paginable.getTag());
                    if(fragmentable == null){
                        fragmentable = listener.getFragmentableInstance(paginable);
                    }
                    if(fragmentableList.add(fragmentable)){
                        Log.d(TAG, "restorePaginableInstance: fragment added");
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
                paginables[i].setTag(getItem(i).getTag());
            }
            instance.putParcelableArray(PAGINABLES, paginables);
        }
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public Fragmentable getItem(int position){
        return fragmentableList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentableList.size();
    }

    @Override
    public long getItemId(int position){
        return getItem(position).getPaginable().getItemId();
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

    // +++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > NO_ITEMS && position < fragmentableList.size();
    }
}
