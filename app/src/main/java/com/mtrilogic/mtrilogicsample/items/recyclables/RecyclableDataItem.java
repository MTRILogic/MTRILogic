package com.mtrilogic.mtrilogicsample.items.recyclables;

import android.content.Context;
import android.view.View;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.BindingRecyclable;

import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemDataBinding;
import com.mtrilogic.mtrilogicsample.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class RecyclableDataItem extends BindingRecyclable<DataModel, ItemDataBinding> {

    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public RecyclableDataItem(@NonNull ItemDataBinding binding, @NonNull RecyclableAdapterListener listener){
        super(binding, listener);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        chkItem = binding.chkItem;
        chkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChecked();
            }
        });
        lblTitle = binding.lblTitle;
        lblContent = binding.lblContent;
        ImageButton btnDelete = binding.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onBindHolder(@NonNull Modelable modelable){
        model = (DataModel) modelable;
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, position));
    }

    @Override
    public void onChanged(DataModel dataModel) {

    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        RecyclableAdapter adapter = listener.getRecyclableAdapter();
        if (adapter != null){
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Item [" + position + "] set to " + checked);
        }
    }
}
