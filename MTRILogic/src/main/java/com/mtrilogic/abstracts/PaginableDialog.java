package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.BaseDialogListener;
import com.mtrilogic.interfaces.PaginableAdapterListener;
import com.mtrilogic.interfaces.PaginableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultPaginable;

@SuppressWarnings("unused")
public abstract class PaginableDialog<P extends Page> extends BaseDialog<P> implements PaginableAdapterListener, PaginableItemListener {

    protected final Listable<Page> pageListable;
    protected PaginableAdapter adapter;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public PaginableDialog(@NonNull Context context, @NonNull Listable<Page> pageListable, @NonNull BaseDialogListener<P> listener) {
        super(context, listener);
        this.pageListable = pageListable;
    }

    protected PaginableDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull Listable<Page> pageListable, @NonNull BaseDialogListener<P> listener) {
        super(context, cancelable, cancelListener, listener);
        this.pageListable = pageListable;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public final Listable<Page> getPageListable() {
        return pageListable;
    }

    @NonNull
    @Override
    public final PaginableAdapter getPaginableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Paginable<? extends Page> getPaginable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultPaginable(inflater, parent, this);
    }

    @Override
    public boolean onPaginableLongClick(@NonNull View itemView, @NonNull Page page, int position) {
        return false;
    }

    @Override
    public void onPaginableClick(@NonNull View itemView, @NonNull Page page, int position) {

    }

     /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el PaginableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param pager el ViewPager.
     */
    protected final void initPaginableAdapter(@NonNull ViewPager pager){
        adapter = new PaginableAdapter(getLayoutInflater(), this);
        pager.setAdapter(adapter);
    }
}
