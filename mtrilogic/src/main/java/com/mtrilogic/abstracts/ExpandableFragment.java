package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused"})
public abstract class ExpandableFragment<P extends MapPaginable<Modelable>, L extends FragmentListener>
        extends Fragmentable<P, L> implements ExpandableListener, ExpandableItemListener {
    protected ExpandableAdapter adapter;
    protected ExpandableView lvwItems;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ExpandableFragment(@NonNull Paginable paginable) {
        super(paginable);
    }

    public ExpandableFragment() {
        super();
    }

    // ================< PROTECTED METHODS >========================================================

    protected void bindExpandable(@NonNull Context context, @NonNull ExpandableView lvwItems,
                                  @NonNull P page, int groupTypeCount, int childTypeCount){
        Listable<Modelable> groupListable = page.getGroupListable();
        Mapable<Modelable> childMapable = page.getChildMapable();
        if (groupListable != null && childMapable != null) {
            adapter = new ExpandableAdapter(context, this, groupListable, childMapable,
                    groupTypeCount, childTypeCount);
            this.lvwItems = lvwItems;
            lvwItems.setAdapter(adapter);
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null && lvwItems != null){
            lvwItems.restoreFromState(args);
        }
    }

    @Override
    public void onPause() {
        Bundle args = getArguments();
        if (args != null && lvwItems != null){
            lvwItems.saveToState(args);
        }
        super.onPause();
    }

    @Override
    public ExpandableView getExpandableView() {
        return lvwItems;
    }

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }

    @Override
    public boolean onItemTouch(@NonNull View view, @NonNull MotionEvent event, @NonNull Modelable modelable,
                               int position) {
        return false;
    }

    @Override
    public boolean onItemLongClick(@NonNull View view, @NonNull Modelable modelable, int position) {
        return false;
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Modelable modelable, int position) {

    }
}
