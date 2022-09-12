package com.mtrilogic.abstracts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.OnTaskCompleteListener;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultRecyclable;

@SuppressWarnings("unused")
public abstract class RecyclableDialog<M extends Model> extends BaseDialog<M> implements RecyclableAdapterListener, RecyclableItemListener {
    protected final Listable<Model> modelListable;
    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public RecyclableDialog(@NonNull Context context, @NonNull Listable<Model> modelListable, @NonNull OnTaskCompleteListener<M> listener) {
        super(context, listener);
        this.modelListable = modelListable;
    }

    protected RecyclableDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, @NonNull Listable<Model> modelListable, @NonNull OnTaskCompleteListener<M> listener) {
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
    public final RecyclableAdapter getRecyclableAdapter() {
        if (adapter == null){
            Base.makeLog("RecyclableDialog: RecyclableAdapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final RecyclerView getRecyclerView() {
        if (lvwItems == null){
            Base.makeLog("RecyclableDialog: RecyclerView is null");
        }
        return lvwItems;
    }

    @NonNull
    @Override
    public Recyclable<? extends Model> getRecyclable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new DefaultRecyclable(inflater, parent, this);
    }

    @Override
    public boolean onRecyclableLongClick(@NonNull View itemView, @NonNull Model model, int position) {
        return false;
    }

    @Override
    public void onRecyclableClick(@NonNull View itemView, @NonNull Model model, int position) {

    }

    /*==============================================================================================
    PROTECTED METHOD
    ==============================================================================================*/

    /**
     * Inicializa el RecyclerView y el RecyclableAdapter
     * ATENCIÓN!!!: Este método debe llamarse dentro de onCreateView
     * @param lvwItems the recyclerView view.
     * @param manager the layout manager (LinearLayoutManager or GridLayoutManager)
     */
    protected final void initRecyclerViewAdapter(@NonNull RecyclerView lvwItems, @NonNull RecyclerView.LayoutManager manager){
        adapter = new RecyclableAdapter(getLayoutInflater(), this);
        lvwItems.setAdapter(adapter);
        lvwItems.setLayoutManager(manager);
        this.lvwItems = lvwItems;
    }
}
