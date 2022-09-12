package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultInflatable;

@SuppressWarnings({"unused"})
public abstract class InflatableDialogFragment<P extends ListablePage<Model>> extends BaseDialogFragment<P> implements InflatableAdapterListener, InflatableItemListener {
    protected InflatableAdapter adapter;
    protected ListView lvwItems;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public final Listable<Model> getModelListable() {
        if (page == null){
            Base.makeLog("InflatableDialogFragment: ListablePage is null");
        }
        return page.getListable();
    }

    @NonNull
    @Override
    public final InflatableAdapter getInflatableAdapter() {
        if (adapter == null){
            Base.makeLog("InflatableDialogFragment: InflatableAdapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final ListView getListView() {
        if (lvwItems == null){
            Base.makeLog("InflatableDialogFragment: ListView is null");
        }
        return lvwItems;
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
     * Inicializa el Listview y el InflatableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param lvwItems el ListView.
     * @param typeCount el número de items diferentes.
     */
    protected final void initListViewAdapter(@NonNull ListView lvwItems, int typeCount){
        adapter = new InflatableAdapter(getLayoutInflater(), typeCount, this);
        lvwItems.setAdapter(adapter);
        this.lvwItems = lvwItems;
    }
}
