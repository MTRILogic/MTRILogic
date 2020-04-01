package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.interfaces.PaginableItemListener;

import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class PageInflatable<P extends Paginable> extends LiveData<P> implements Observer<P>, OnMakeToastListener {
    protected final PaginableItemListener listener;
    protected final View itemView;

    protected int position;
    protected P page;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract P getPageFromPaginable(@NonNull Paginable paginable);
    protected abstract void onBindPage();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public PageInflatable(@NonNull View itemView, @NonNull PaginableItemListener listener) {
        this.itemView = itemView;
        this.listener = listener;
    }

    public PageInflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                          @NonNull PaginableItemListener listener) {
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Paginable paginable, int position){
        page = getPageFromPaginable(paginable);
        this.position = position;
        if (page != null){
            onBindPage();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        if (page != null){
            PaginableAdapter adapter = listener.getPaginableAdapter();
            if (adapter != null){
                Listable<Paginable> listable = adapter.getListable();
                if (listable != null){
                    ArrayList<Paginable> list = listable.getList();
                    if (list != null){
                        if (list.remove(page)){
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    public void onBindItemView(){

    }

    @Override
    public void onMakeToast(String line) {
        listener.onMakeToast(line);
    }

    @Override
    public void onMakeLog(String line) {
        listener.onMakeLog(line);
    }
}
