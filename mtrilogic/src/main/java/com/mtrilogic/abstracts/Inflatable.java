package com.mtrilogic.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.Bindable;
import com.mtrilogic.interfaces.InflatableItemListener;

import java.util.ArrayList;

@SuppressWarnings({"unused"})
public abstract class Inflatable<M extends Modelable, L extends InflatableItemListener>
        extends LiveData<M> implements Bindable<M>, Observer<M> {
    protected final View itemView;
    protected final L listener;

    protected int position;
    protected M model;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public Inflatable(@NonNull View itemView, @NonNull L listener){
        this.itemView = itemView;
        this.listener = listener;
    }

    public Inflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                      @NonNull L listener){
        itemView = inflater.inflate(resource, parent, false);
        this.listener = listener;
        onBindItemView();
    }

    // ================< PUBLIC METHODS >===========================================================

    public final View getItemView() {
        return itemView;
    }

    public final void bindModel(@NonNull Modelable modelable, int position){
        model = getModelFromModelable(modelable);
        this.position = position;
        if (model != null) {
            onBindModel();
        }
    }

    // ================< PROTECTED METHODS >========================================================

    protected final M getModel(){
        return model;
    }

    protected final int getPosition(){
        return position;
    }

    protected L getListener() {
        return listener;
    }

    protected final void autoDelete(){
        if (model != null){
            InflatableAdapter adapter = listener.getInflatableAdapter();
            if (adapter != null){
                Listable<Modelable> listable = adapter.getListable();
                if (listable != null){
                    ArrayList<Modelable> list = listable.getList();
                    if (list != null){
                        if (list.remove(model)){
                            adapter.notifyDataSetChanged();
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
