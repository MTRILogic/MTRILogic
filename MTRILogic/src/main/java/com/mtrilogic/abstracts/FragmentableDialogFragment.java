package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.mtrilogic.fragments.DefaultBaseFragment;

@SuppressWarnings("unused")
public abstract class FragmentableDialogFragment<P extends ListablePage<Page>> extends BaseDialogFragment<P> implements FragmentableAdapterListener, FragmentableItemListener {

    protected FragmentableAdapter adapter;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public final Listable<Page> getPageListable() {
        return page.getListable();
    }

    @NonNull
    @Override
    public final FragmentableAdapter getFragmentableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Fragmentable getFragmentable(int viewType) {
        return new DefaultBaseFragment();
    }

    @Override
    public void onPositionChanged(@NonNull Fragmentable fragmentable, int position) {

    }

    @Override
    public void onNewTagName(@NonNull String oldTag, @NonNull String newTag) {
        if (oldTag.equals(page.getTagName())){
            page.setTagName(newTag);
        }
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
        adapter = new FragmentableAdapter(getChildFragmentManager(), this);
        pager.setAdapter(adapter);
    }
}
