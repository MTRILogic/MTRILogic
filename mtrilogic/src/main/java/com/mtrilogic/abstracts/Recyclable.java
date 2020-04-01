package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.RecyclableItemListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public abstract class Recyclable<M extends Modelable, L extends RecyclableItemListener>
        extends RecyclerView.ViewHolder implements Bindable<M>, Observer<M> {
    protected final L listener;

    protected int position;
    protected M model;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Recyclable(@NonNull View itemView, @NonNull L listener){
        super(itemView);
        this.listener = listener;
    }

    public Recyclable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull L listener){
        super(inflater.inflate(resource, parent, false));
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final void bindModel(@NonNull Modelable modelable, int position){
        model = getModelFromModelable(modelable);
        this.position = position;
        if (model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected M getModel() {
        return model;
    }

    protected final void autoDelete(){
        if (model != null){
            RecyclableAdapter adapter = listener.getRecyclableAdapter();
            if (adapter != null){
                Listable<Modelable> listable = adapter.getListable();
                if (listable != null){
                    ArrayList<Modelable> list = listable.getList();
                    if (list != null){
                        if (list.remove(model)){
                            adapter.notifyItemRemoved(position);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onChanged(M model) {

    }

    @Override
    public void onBindItemView() {

    }

    @Override
    public void onMakeToast(String line) {
        listener.onMakeToast(line);
    }

    @Override
    public void onMakeLog(String line) {
        listener.onMakeLog(line);
    }
}
