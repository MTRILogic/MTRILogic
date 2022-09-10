package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Mappable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultExpandableChild;
import com.mtrilogic.mtrilogic.items.DefaultExpandableGroup;

@SuppressWarnings("unused")
public abstract class ExpandableFragment<P extends MappablePage> extends BaseFragment<P> implements ExpandableAdapterListener, ExpandableItemListener {
    protected ExpandableAdapter adapter;
    protected ExpandableListView lvwItems;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Base.restoreTopIndex(lvwItems, getArguments());
    }

    @Override
    public void onDestroyView() {
        Base.saveTopIndex(lvwItems, getArguments());
        super.onDestroyView();
    }

    @NonNull
    @Override
    public ExpandableGroup<? extends Model> getExpandableGroup(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultExpandableGroup(inflater, parent, this);
    }

    @NonNull
    @Override
    public ExpandableChild<? extends Model> getExpandableChild(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultExpandableChild(inflater, parent, this);
    }

    @NonNull
    @Override
    public final Mappable<Model> getModelMappable() {
        return page.getModelableMapable();
    }

    @NonNull
    @Override
    public final ExpandableAdapter getExpandableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public final ExpandableListView getExpandableListView() {
        return lvwItems;
    }

    @Override
    public boolean onExpandableChildLongClick(@NonNull View itemView, @NonNull Model model, int groupPosition, int childPosition, boolean lastChild) {
        return false;
    }

    @Override
    public boolean onExpandableGroupLongClick(@NonNull View itemView, @NonNull Model model, int groupPosition, boolean expanded) {
        return false;
    }

    @Override
    public void onExpandableChildClick(@NonNull View itemView, @NonNull Model model, int groupPosition, int childPosition, boolean lastChild) {

    }

    @Override
    public void onExpandableGroupClick(@NonNull View itemView, @NonNull Model model, int groupPosition, boolean expanded) {

    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el ExpandableView y el ExpandableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de <b>onCreateViewFragment()</b>.
     * @param lvwItems el ExpandableView
     * @param groupTypeCount número de tipos para grupos diferentes (por default = 1)
     * @param childTypeCount número de tipos para hijos diferentes (por default = 1)
     */
    protected void initExpandableListViewAdapter(@NonNull ExpandableListView lvwItems, int groupTypeCount, int childTypeCount){
        adapter = new ExpandableAdapter(getLayoutInflater(), groupTypeCount, childTypeCount, this);
        lvwItems.setAdapter(adapter);
        this.lvwItems = lvwItems;
    }
}
