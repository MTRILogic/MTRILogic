package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.Page;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.PaginableAdapterListener;

import java.util.ArrayList;

@SuppressWarnings("unused")
public final class PaginableAdapter extends PagerAdapter {

    private final PaginableAdapterListener listener;
    private final LayoutInflater inflater;

    private final ArrayList<View> itemViewList;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public PaginableAdapter(@NonNull LayoutInflater inflater, @NonNull PaginableAdapterListener listener){
        itemViewList = new ArrayList<>();
        this.inflater = inflater;
        this.listener = listener;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public int getCount() {
        return getPageListable().getCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Paginable<? extends Page> paginable = (Paginable<? extends Page>) object;
        return paginable.isFromView(view);
    }

    @NonNull
    @Override
    public Paginable<? extends Page> instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = null;
        if (position < itemViewList.size()){
            itemView = itemViewList.get(position);
        }
        Page page = getPage(position);
        Paginable<? extends Page> paginable;
        if (itemView != null){
            paginable = (Paginable<? extends Page>) itemView.getTag();
        }else {
            int viewType = page.getViewType();
            paginable = listener.getPaginable(viewType, inflater, container);
            itemView = paginable.getItemView();
            itemView.setTag(paginable);
            while (itemViewList.size() <= position){
                itemViewList.add(null);
            }
            itemViewList.set(position, itemView);
            container.addView(itemView);
        }
        paginable.bindPage(page, position);
        return paginable;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = itemViewList.set(position, null);
        container.removeView(view);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPage(position).getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Paginable<? extends Page> paginable = (Paginable<? extends Page>) object;
        int position = getPageListable().getPosition(paginable.getPage());
        if (paginable.getPosition() != position){
            listener.onPositionChanged(paginable, position);
            if (position > Base.INVALID_POSITION){
                paginable.updatePosition(position);
                return position;
            }
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
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
