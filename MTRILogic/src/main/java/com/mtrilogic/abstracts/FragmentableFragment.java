package com.mtrilogic.abstracts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.mtrilogic.fragments.DefaultBaseFragment;

@SuppressWarnings("unused")
public abstract class FragmentableFragment<P extends ListablePage<Page>> extends BaseFragment<P> implements FragmentableAdapterListener, FragmentableItemListener {
    protected FragmentableAdapter adapter;
    protected ViewPager pager;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragment getFragment(@NonNull Page page, int position) {
        return BaseFragment.getInstance(new DefaultBaseFragment(), page, position);
    }

    @Override
    public void onPositionChanged(int position) {
        Base.makeLog("FragmentableFragment: Position = " + position);
    }

    @NonNull
    @Override
    public final Listable<Page> getPageListable() {
        if (page == null){
            Base.makeLog("FragmentableFragment: Page is null");
        }
        return page.getListable();
    }

    @NonNull
    @Override
    public final FragmentableAdapter getFragmentableAdapter() {
        if (adapter == null){
            Base.makeLog("FragmentableFragment: Adapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final ViewPager getViewPager() {
        if (pager == null){
            Base.makeLog("FragmentableFragment: ViewPager is null");
        }
        return pager;
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
     * Inicializa el ViewPager y el PaginableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param pager el ViewPager.
     */
    protected void initViewPagerAdapter(@NonNull ViewPager pager){
        adapter = new FragmentableAdapter(getChildFragmentManager(), this);
        pager.setAdapter(adapter);
        this.pager = pager;
    }
}
