package com.mtrilogic.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.BaseDialogListener;
import com.mtrilogic.interfaces.Fragmentable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableItemListener;
import com.mtrilogic.mtrilogic.fragments.DefaultBaseFragment;

@SuppressWarnings("unused")
public abstract class FragmentableDialog<P extends Page> extends BaseDialog<P> implements FragmentableAdapterListener, FragmentableItemListener {

    protected final Listable<Page> pageListable;
    protected FragmentableAdapter adapter;
    protected String tagName;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public FragmentableDialog(@NonNull Context context, @NonNull Listable<Page> pageListable, @NonNull BaseDialogListener<P> listener) {
        super(context, listener);
        this.pageListable = pageListable;
    }

    protected FragmentableDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull Listable<Page> pageListable, @NonNull BaseDialogListener<P> listener) {
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
        if (oldTag.equals(tagName)){
            tagName = newTag;
        }
    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el PaginableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param manager el fragment manager.
     * @param pager el ViewPager.
     */
    protected final void initPaginableAdapter(@NonNull FragmentManager manager, @NonNull ViewPager pager){
        adapter = new FragmentableAdapter(manager, this);
        pager.setAdapter(adapter);
    }
}
