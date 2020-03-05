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
import com.mtrilogic.interfaces.PageInflatableListener;

import java.util.ArrayList;

public class PaginableAdapter extends PagerAdapter {
    private ArrayList<Paginable> paginableList;
    private ArrayList<View> itemViewList;
    private PageInflatableListener listener;
    private LayoutInflater inflater;

    public PaginableAdapter(Context context, ArrayList<Paginable> paginableList, PageInflatableListener listener){
        inflater = LayoutInflater.from(context);
        this.paginableList = paginableList;
        itemViewList = new ArrayList<>();
        this.listener = listener;
    }

    public ArrayList<Paginable> getPaginableList() {
        return paginableList;
    }

    @Override
    public int getCount() {
        return paginableList.size();
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
        Paginable paginable = getItem(position);
        int viewType = paginable.getViewType();
        PageInflatable inflatable = listener.getPageInflatable(viewType, inflater, container);
        itemView = inflatable.getItemView();
        while (itemViewList.size() <= position){
            itemViewList.add(null);
        }
        itemViewList.set(position, itemView);
        container.addView(itemView);
        inflatable.bindHolder(paginable, position);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        itemViewList.set(position, null);
    }

    @Override
    public float getPageWidth(int position) {
        return getItem(position).getPageWidth();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);// pending
    }

    private Paginable getItem(int position){
        return paginableList.get(position);
    }
}