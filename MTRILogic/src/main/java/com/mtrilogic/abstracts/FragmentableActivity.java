package com.mtrilogic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.mtrilogic.fragments.DefaultBaseFragment;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableItemListener;

@SuppressWarnings("unused")
public abstract class FragmentableActivity extends BaseActivity implements FragmentableAdapterListener, FragmentableItemListener {

    protected Listable<Page> pageListable;
    protected FragmentableAdapter adapter;

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            pageListable = new Listable<>(savedInstanceState);
        }else {
            pageListable = new Listable<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        pageListable.saveToData(outState);
        super.onSaveInstanceState(outState);
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
    public final FragmentableAdapter getFragmentableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Fragment getFragment(@NonNull Page page, int position) {
        return BaseFragment.getInstance(new DefaultBaseFragment(), page, position);
    }

    @Override
    public void onPositionChanged(int oldPosition, int position) {

    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el PaginableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param pager el ViewPager.
     */
    public final void initPaginableAdapter(@NonNull ViewPager pager){
        adapter = new FragmentableAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
    }
}
