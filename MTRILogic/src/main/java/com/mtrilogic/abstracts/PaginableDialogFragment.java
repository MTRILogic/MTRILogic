package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.PaginableAdapterListener;
import com.mtrilogic.interfaces.PaginableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultPaginable;

@SuppressWarnings({"unused"})
public abstract class PaginableDialogFragment<P extends ListablePage<Page>> extends BaseDialogFragment<P> implements PaginableAdapterListener, PaginableItemListener {
    protected PaginableAdapter adapter;
    protected ViewPager pager;

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
        if (page == null){
            Base.makeLog("PaginableDialogFragment: Page is null");
        }
        return page.getListable();
    }

    @NonNull
    @Override
    public final PaginableAdapter getPaginableAdapter() {
        if (adapter == null){
            Base.makeLog("PaginableDialogFragment: Adapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final ViewPager getViewPager() {
        if (pager == null){
            Base.makeLog("PaginableDialogFragment: ViewPager is null");
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