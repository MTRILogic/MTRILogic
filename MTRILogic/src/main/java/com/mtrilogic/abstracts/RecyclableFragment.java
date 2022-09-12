package com.mtrilogic.abstracts;

import static com.mtrilogic.classes.Base.makeLog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.mtrilogic.items.DefaultRecyclable;

@SuppressWarnings("unused")
public abstract class RecyclableFragment<P extends ListablePage<Model>> extends BaseFragment<P> implements RecyclableAdapterListener, RecyclableItemListener {
    protected RecyclableAdapter adapter;
    protected RecyclerView lvwItems;

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public final Listable<Model> getModelListable() {
        if (page == null){
            makeLog("RecyclableFragment: ListablePage is null");
        }
        return page.getListable();
    }

    @NonNull
    @Override
    public final RecyclableAdapter getRecyclableAdapter() {
        if (adapter == null){
            makeLog("RecyclableFragment: RecyclableAdapter is null");
        }
        return adapter;
    }

    @NonNull
    @Override
    public final RecyclerView getRecyclerView() {
        if (lvwItems == null){
            makeLog("RecyclableFragment: RecyclerView is null");
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
        makeLog("Item long click: Id = " + model.getItemId() + ", Pos = " + position);
        return true;
    }

    @Override
    public void onRecyclableClick(@NonNull View itemView, @NonNull Model model, int position) {
        makeLog("Item click: Id = " + model.getItemId() + ", Pos = " + position);
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
