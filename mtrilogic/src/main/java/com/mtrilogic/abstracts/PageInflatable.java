package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.interfaces.PaginableAdapterListener;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class PageInflatable<P extends Paginable> extends LiveData<P> implements Observer<P> {
    protected final PaginableAdapterListener listener;
    protected final View itemview;

    protected int position;
    protected P page;

    // ================< PROTECTED ABSTRACT METHODS >===============================================

    protected abstract P getPageFromPaginable(@NonNull Paginable paginable);
    protected abstract void onBindHolder();

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public PageInflatable(@NonNull View itemView, @NonNull PaginableAdapterListener listener) {
        this.itemview = itemView;
        this.listener = listener;
    }

    public PageInflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                          @NonNull PaginableAdapterListener listener) {
        itemview = inflater.inflate(resource, parent, false);
        this.listener = listener;
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemview;
    }

    public final void bindHolder(@NonNull Paginable paginable, int position){
        page = getPageFromPaginable(paginable);
        this.position = position;
        onBindHolder();
    }

    // ================< PROTECTED METHODS >========================================================

    protected final void autoDelete(){
        PaginableAdapter adapter = listener.getPaginableAdapter();
        if (adapter != null){
            if (adapter.getPaginableList().remove(page)){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
