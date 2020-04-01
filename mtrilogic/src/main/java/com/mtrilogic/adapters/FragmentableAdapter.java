package com.mtrilogic.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public class FragmentableAdapter extends FragmentPagerAdapter{
    private FragmentableListener listener;
    private Listable<Paginable> listable;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public FragmentableAdapter(@NonNull FragmentManager manager, @NonNull Listable<Paginable> listable,
                               @NonNull FragmentableListener listener){
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listable = listable;
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public Listable<Paginable> getListable() {
        return listable;
    }

    public boolean setListable(Listable<Paginable> listable) {
        if (this.listable != listable) {
            this.listable = listable;
            return true;
        }
        return false;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @NonNull
    @Override
    public Fragmentable getItem(int position){
        return listener.getFragmentable(getPaginable(position));
    }

    @Override
    public int getCount(){
        return listable.getList().size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;
        Paginable paginable = fragmentable.getPaginable();
        int position = getPaginableList().indexOf(paginable);
        if (position != Base.INVALID_POSITION){
            fragmentable.onNewPosition(position);
            listener.onChangePosition(position);
            return position;
        }
        if (getCount() == 0){
            listener.onChangePosition(position);
        }
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return getPaginable(position).getPageTitle();
    }

    @Override
    public long getItemId(int position){
        return getPaginable(position).getItemId();
    }

    // ================< PRIVATE METHODS >==========================================================

    private Paginable getPaginable(int position){
        return getPaginableList().get(position);
    }

    private ArrayList<Paginable> getPaginableList(){
        return listable.getList();
    }
}
