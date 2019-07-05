package com.mtrilogic.sampleapp.items.recyclables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class RecyclableDataItem extends Recyclable implements View.OnClickListener{
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int position;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public RecyclableDataItem(Context context, int resource){
        this(context, (RecyclableAdapterListener)context, resource);
    }

    public RecyclableDataItem(Context context, RecyclableAdapterListener listener, int resource){
        super(new View(context), context, listener, resource);
    }

// ++++++++++++++++| PRIVATE CONSTRUCTORS |++++++++++++++++++++++++++++++++++++

    private RecyclableDataItem(View view, Context context, RecyclableAdapterListener listener, int resource){
        super(view, context, listener, resource);
        view.setOnClickListener(this);
        chkItem = view.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public Recyclable getRecyclableHolder(ViewGroup parent){
        Context context = getContext();
        int resource = getLayoutResource();
        View view = LayoutInflater.from(context).inflate(resource, parent,false);
        return new RecyclableDataItem(view, context, getListener(), resource);
    }

    @Override
    public void onBindHolder(Modelable modelable, int position){
        model = (DataModel)modelable;
        this.position = position;
        chkItem.setChecked(model.isChecked());
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, position));
    }

    @Override
    public void onClick(View view){
        RecyclableAdapter adapter = getListener().getRecyclableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                getListener().onMakeToast("Item[" + position + "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.removeModelable(model)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
