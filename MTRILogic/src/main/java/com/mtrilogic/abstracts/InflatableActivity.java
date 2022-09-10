package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultInflatable;

@SuppressWarnings("unused")
public class InflatableActivity extends BaseActivity implements InflatableAdapterListener, InflatableItemListener {
    protected Listable<Model> modelListable;
    protected InflatableAdapter adapter;
    protected ListView lvwItems;

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            modelListable = new Listable<>(savedInstanceState);
        }else {
            modelListable = new Listable<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        modelListable.saveToData(outState);
        super.onSaveInstanceState(outState);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Inflatable<? extends Model> getInflatable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultInflatable(inflater, parent, this);
    }

    @NonNull
    @Override
    public Listable<Model> getModelListable() {
        return modelListable;
    }

    @NonNull
    @Override
    public InflatableAdapter getInflatableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public ListView getListView() {
        return lvwItems;
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
     * Inicializa el Listview y el InflatableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param lvwItems el ListView.
     * @param typeCount el número de items diferentes.
     */
    protected void initListViewAdapter(@NonNull ListView lvwItems, int typeCount){
        adapter = new InflatableAdapter(getLayoutInflater(), typeCount, this);
        lvwItems.setAdapter(adapter);
        this.lvwItems = lvwItems;
    }
}
