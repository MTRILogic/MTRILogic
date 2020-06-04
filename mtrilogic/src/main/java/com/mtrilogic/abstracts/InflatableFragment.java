package com.mtrilogic.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.views.InflatableView;

@SuppressWarnings({"unused"})
public abstract class InflatableFragment<P extends ListPaginable<Modelable>, L extends FragmentListener> extends Fragmentable<P, L> implements InflatableListener, InflatableItemListener {
    protected InflatableAdapter adapter;
    protected InflatableView lvwItems;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableFragment(@NonNull Paginable paginable) {
        super(paginable);
    }

    public InflatableFragment() {
        super();
    }

    // ================< PROTECTED METHODS >========================================================

    protected void bindInflatable(@NonNull Context context, @NonNull InflatableView lvwItems, @NonNull P page, int typeCount){
        Listable<Modelable> listable = page.getListable();
        if (listable != null) {
            adapter = new InflatableAdapter(context, this, listable, typeCount);
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
        if (args != null && lvwItems != null) {
            lvwItems.saveToState(args);
        }
        super.onPause();
    }

    @Override
    public InflatableView getInflatableView() {
        return lvwItems;
    }

    @Override
    public InflatableAdapter getInflatableAdapter(){
        return adapter;
    }
}
