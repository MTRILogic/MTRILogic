package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class FragmentableAdapter extends FragmentPagerAdapter{
    private FragmentableListener listener;
    private ArrayList<Paginable> paginableList;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public FragmentableAdapter(@NonNull FragmentManager manager, @NonNull FragmentableListener listener,
                               @NonNull ArrayList<Paginable> paginableList){
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listener = listener;
        this.paginableList = paginableList;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final int getPaginablePosition(@NonNull Paginable paginable){
        return paginableList.indexOf(paginable);
    }

    public final Paginable[] getPaginableArray(){
        return paginableList.toArray(new Paginable[getCount()]);
    }

    public final ArrayList<Paginable> getPaginableList(){
        return paginableList;
    }

    public final void setPaginableList(@NonNull ArrayList<Paginable> paginableList){
        this.paginableList = paginableList;
    }

    public final boolean addPaginableList(@NonNull ArrayList<Paginable> paginableList){
        return this.paginableList.addAll(paginableList);
    }

    public final boolean insertPaginableList(int position, @NonNull ArrayList<Paginable> paginableList){
        return this.paginableList.addAll(position, paginableList);
    }

    public final boolean removePaginableList(@NonNull ArrayList<Paginable> paginableList){
        return this.paginableList.removeAll(paginableList);
    }

    public final boolean retainPaginableList(@NonNull ArrayList<Paginable> paginableList){
        return this.paginableList .retainAll(paginableList);
    }

    public final Paginable getPaginable(int position){
        return isValidPosition(position) ? paginableList.get(position) : null;
    }

    public final Paginable setPaginable(int position, @NonNull Paginable paginable){
        return isValidPosition(position) ? paginableList.set(position, paginable) : null;
    }

    public final boolean addPaginable(@NonNull Paginable paginable){
        return paginableList.add(paginable);
    }

    public final void insertPaginable(int position, @NonNull Paginable paginable){
        if(isValidPosition(position)){
            paginableList.add(position, paginable);
        }
    }

    public final boolean removePaginable(@NonNull Paginable paginable){
        return paginableList.remove(paginable);
    }

    public final void clearPaginableList(){
        paginableList.clear();
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @NonNull
    @Override
    public Fragmentable getItem(int position){
        Paginable paginable = getPaginableItem(position);
        return listener.getFragmentable(paginable, position);
    }

    @Override
    public int getCount(){
        return paginableList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;
        Paginable paginable = fragmentable.getPaginable();
        if (paginableList.contains(paginable)){
            int position = paginableList.indexOf(paginable);
            fragmentable.onNewPosition(position);
            listener.onPositionChanged(position);
            return position;
        }
        if (getCount() == 0){
            listener.onPositionChanged(Base.INVALID_POSITION);
        }
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getPaginableItem(position).getPageTitle();
    }

    @Override
    public float getPageWidth(int position) {
        return getPaginableItem(position).getPageWidth();
    }

    @Override
    public long getItemId(int position){
        return getPaginableItem(position).getItemId();
    }

    // ================< PRIVATE METHODS >==========================================================

    private boolean isValidPosition(int position){
        return position > Base.INVALID_POSITION && position < getCount();
    }

    @NonNull
    private Paginable getPaginableItem(int position){
        return paginableList.get(position);
    }
}
