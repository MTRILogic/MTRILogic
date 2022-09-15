package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.interfaces.OnTaskCompleteListener;
import com.mtrilogic.mtrilogic.items.DefaultInflatable;

@SuppressWarnings("unused")
public abstract class InflatableDialog<M extends Model> extends BaseDialog<M> implements InflatableAdapterListener, InflatableItemListener {
    protected final Listable<Model> modelListable;
    protected InflatableAdapter adapter;
    protected AbsListView lvwItems;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public InflatableDialog(@NonNull Context context, @NonNull Listable<Model> modelListable, @NonNull OnTaskCompleteListener<M> listener) {
        super(context, listener);
        this.modelListable = modelListable;
    }

    protected InflatableDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull Listable<Model> modelListable, @NonNull OnTaskCompleteListener<M> listener) {
        super(context, cancelable, cancelListener, listener);
        this.modelListable = modelListable;
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public final Listable<Model> getModelListable() {
        return modelListable;
    }

    @NonNull
    @Override
    public final InflatableAdapter getInflatableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public final AbsListView getListView() {
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
    protected final void initListViewAdapter(@NonNull AbsListView lvwItems, int typeCount){
        adapter = new InflatableAdapter(getLayoutInflater(), typeCount, this);
        lvwItems.setAdapter(adapter);
        this.lvwItems = lvwItems;
    }
}
