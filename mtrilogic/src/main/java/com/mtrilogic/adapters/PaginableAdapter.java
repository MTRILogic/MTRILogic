package com.mtrilogic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.mtrilogic.abstracts.PageInflatable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.PageInflatableListener;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class PaginableAdapter extends PagerAdapter {
    private Listable<Paginable> listable;
    private ArrayList<View> itemViewList;
    private PageInflatableListener listener;
    private LayoutInflater inflater;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public PaginableAdapter(@NonNull Context context, @NonNull Listable<Paginable> listable,
                            @NonNull PageInflatableListener listener){
        inflater = LayoutInflater.from(context);
        itemViewList = new ArrayList<>();
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

    @Override
    public int getCount() {
        return getPaginableList().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView;
        if (itemViewList.size() > position){
            itemView = itemViewList.get(position);
            if (itemView != null){
                return itemView;
            }
        }
        Paginable paginable = getPaginable(position);
        int viewType = paginable.getViewType();
        PageInflatable inflatable = listener.getPageInflatable(viewType, inflater, container);
        itemView = inflatable.getItemView();
        while (itemViewList.size() <= position){
            itemViewList.add(null);
        }
        itemViewList.set(position, itemView);
        container.addView(itemView);
        inflatable.bindModel(paginable, position);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        itemViewList.set(position, null);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPaginable(position).getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object); // only default implementation (No Add/Remove items)
    }

    // ================< PRIVATE METHODS >==========================================================

    private Paginable getPaginable(int position){
        return getPaginableList().get(position);
    }

    private ArrayList<Paginable> getPaginableList(){
        return listable.getList();
    }
}