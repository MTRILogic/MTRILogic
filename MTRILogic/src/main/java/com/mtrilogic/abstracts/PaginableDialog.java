package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.OnDialogDoneListener;
import com.mtrilogic.interfaces.PaginableAdapterListener;
import com.mtrilogic.interfaces.PaginableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultPaginable;

@SuppressWarnings("unused")
public class PaginableDialog<P extends ListablePage<Page>> extends BaseDialog<P> implements PaginableAdapterListener, PaginableItemListener {
    protected PaginableAdapter adapter;
    protected ViewPager pager;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public PaginableDialog(@NonNull Context context, @NonNull P page, @NonNull OnDialogDoneListener<P> listener) {
        super(context, page, listener);
    }

    protected PaginableDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull P page, @NonNull OnDialogDoneListener<P> listener) {
        super(context, cancelable, cancelListener, page, listener);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Paginable<? extends Page> getPaginable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultPaginable(inflater, parent, this);
    }

    @NonNull
    @Override
    public final Listable<Page> getPageListable() {
        return page.getListable();
    }

    @NonNull
    @Override
    public final PaginableAdapter getPaginableAdapter() {
        if (adapter == null){
            Base.makeLog("PaginableDialog: PaginableAdapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final ViewPager getViewPager() {
        if (pager == null){
            Base.makeLog("PaginableDialog: ViewPager is null");
        }
        return pager;
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
     * Inicializa el ViewPager y el PaginableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param pager el ViewPager.
     */
    protected void initViewPagerAdapter(@NonNull ViewPager pager){
        adapter = new PaginableAdapter(getLayoutInflater(), this);
        pager.setAdapter(adapter);
        this.pager = pager;
    }
}
