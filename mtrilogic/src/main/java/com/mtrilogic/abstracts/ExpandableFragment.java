package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.views.ExpandableView;

@SuppressWarnings({"unused"})
public abstract class ExpandableFragment<P extends MapPaginable<Modelable>>
        extends Fragmentable<P> implements ExpandableListener, ExpandableAdapterListener {

    protected ExpandableAdapter adapter;
    protected ExpandableView lvwItems;

    // ================< PROTECTED METHODS >========================================================

    protected void bindExpandable(@NonNull ExpandableView lvwItems, int groupTypeCount,
                                  int childTypeCount){
        this.lvwItems = lvwItems;
        Context context = getContext();
        if (context != null) {
            Listable<Modelable> groupListable = page.getGroupListable();
            Mapable<Modelable> childMapable = page.getChildMapable();
            adapter = new ExpandableAdapter(context, this, groupListable, childMapable,
                    groupTypeCount, childTypeCount);
            lvwItems.setAdapter(adapter);
        }
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (args != null && lvwItems != null){
            lvwItems.restoreFromState(args);
        }
    }

    @Override
    public void onPause() {
        if (args != null && lvwItems != null){
            lvwItems.saveToState(args);
        }
        super.onPause();
    }

    @Override
    public void onNewPosition(int position) {

    }

    @Override
    public ExpandableView getExpandableView() {
        return lvwItems;
    }

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }
}
