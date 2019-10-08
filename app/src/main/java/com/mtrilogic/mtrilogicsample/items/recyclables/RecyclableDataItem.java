package com.mtrilogic.mtrilogicsample.items.recyclables;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class RecyclableDataItem extends Recyclable implements View.OnClickListener{
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int position;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public RecyclableDataItem(Context context, int resource, ViewGroup parent){
        this(context, resource, parent, (RecyclableAdapterListener)context);
    }

    public RecyclableDataItem(Context context, int resource, ViewGroup parent,
                              RecyclableAdapterListener listener){
        super(context, resource, parent, listener);
        itemView.setOnClickListener(this);
        chkItem = itemView.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                listener.onMakeToast("Item[" + position + "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.removeModelable(model)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
