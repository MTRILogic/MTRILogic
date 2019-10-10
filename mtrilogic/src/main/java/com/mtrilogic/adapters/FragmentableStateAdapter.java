package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class FragmentableStateAdapter extends FragmentStatePagerAdapter{
    private static final String TAG = "FragmentStateAdapter";
    private FragmentableListener listener;
    private ArrayList<Paginable> paginableList;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public FragmentableStateAdapter(FragmentManager manager, FragmentableListener listener, ArrayList<Paginable> paginableList){
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listener = listener;
        this.paginableList = paginableList;
    }

// ++++++++++++++++| PUBLIC METHODS |++++++++++++++++++++++++++++++++++++++++++

    public int getPaginablePosition(Paginable paginable){
        return paginableList.indexOf(paginable);
    }

    public Paginable[] getPaginableArray(){
        return paginableList.toArray(new Paginable[getCount()]);
    }

    public ArrayList<Paginable> getPaginableList(){
        return paginableList;
    }

    public void setPaginableList(ArrayList<Paginable> paginableList){
        this.paginableList = paginableList;
    }

    public boolean addPaginableList(ArrayList<Paginable> paginableList){
        return this.paginableList.addAll(paginableList);
    }

    public boolean insertPaginableList(int position, ArrayList<Paginable> paginableList){
        return this.paginableList.addAll(position, paginableList);
    }

    public boolean removePaginaleList(ArrayList<Paginable> paginableList){
        return this.paginableList.removeAll(paginableList);
    }

    public boolean retainPaginableList(ArrayList<Paginable> paginableList){
        return this.paginableList.retainAll(paginableList);
    }

    public Paginable getPaginable(int position){
        return isValidPosition(position) ? paginableList.get(position) : null;
    }

    public Paginable setPaginable(int position, Paginable paginable){
        return isValidPosition(position) ? paginableList.set(position, paginable) : null;
    }

    public boolean addPaginable(Paginable paginable){
        return paginableList.add(paginable);
    }

    public void insertPaginable(int position, Paginable paginable){
        paginableList.add(position, paginable);
    }

    public boolean removePaginable(Paginable paginable){
        return paginableList.remove(paginable);
    }

    public void clearPaginableList(){
        paginableList.clear();
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @NonNull
    @Override
    public Fragmentable getItem(int position){
        Paginable paginable = paginableList.get(position);
        return listener.getFragmentable(paginable, position);
    }

    @Override
    public int getCount(){
        return paginableList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        Fragmentable fragmentable = (Fragmentable)object;
        Paginable paginable = fragmentable.getPage();
        int position = fragmentable.getPosition();
        if(position == paginableList.indexOf(paginable)){
            return POSITION_UNCHANGED;
        }
        listener.onPositionChanged(position);
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return paginableList.get(position).getPageTitle();
    }

// ++++++++++++++++| PRIVATE METHODS |+++++++++++++++++++++++++++++++++++++++++

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getCount();
    }
}
