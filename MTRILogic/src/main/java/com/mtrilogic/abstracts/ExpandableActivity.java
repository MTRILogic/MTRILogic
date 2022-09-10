package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Mappable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultExpandableChild;
import com.mtrilogic.mtrilogic.items.DefaultExpandableGroup;

@SuppressWarnings("unused")
public abstract class ExpandableActivity extends BaseActivity implements ExpandableAdapterListener, ExpandableItemListener {
    protected Mappable<Model> modelMappable;
    protected ExpandableAdapter adapter;
    protected ExpandableListView lvwItems;

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            modelMappable = new Mappable<>(savedInstanceState);
        }else {
            modelMappable = new Mappable<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        modelMappable.saveToData(outState);
        super.onSaveInstanceState(outState);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

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
    public Mappable<Model> getModelMappable() {
        return modelMappable;
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

    @NonNull
    @Override
    public ExpandableAdapter getExpandableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public ExpandableListView getExpandableListView() {
        return lvwItems;
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
