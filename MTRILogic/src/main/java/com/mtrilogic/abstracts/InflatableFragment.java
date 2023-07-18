package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultInflatable;

@SuppressWarnings("unused")
public abstract class InflatableFragment<P extends ListablePage<Model>> extends BaseFragment<P> implements InflatableAdapterListener, InflatableItemListener {

    protected InflatableAdapter adapter;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Base.restoreTopIndex(getListView(), getArguments());
    }

    @Override
    public void onDestroyView() {
        Base.saveTopIndex(getListView(), getArguments());
        super.onDestroyView();
    }

    @NonNull
    @Override
    public final Listable<Model> getModelListable() {
        return page.getListable();
    }

    @NonNull
    @Override
    public final InflatableAdapter getInflatableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Inflatable<? extends Model> getInflatable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultInflatable(inflater, parent, this);
    }

    @Override
    public boolean onItemLongClick(@NonNull View itemView, @NonNull Model model, int position) {
        return false;
    }

    @Override
    public void onItemClick(@NonNull View itemView, @NonNull Model model, int position) {

    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el InflatableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param lvwItems el ListView.
     * @param typeCount el número de items diferentes.
     */
    protected final void initInflatableAdapter(@NonNull AbsListView lvwItems, int typeCount){
        adapter = new InflatableAdapter(getLayoutInflater(), typeCount, this);
        lvwItems.setAdapter(adapter);
    }
}
