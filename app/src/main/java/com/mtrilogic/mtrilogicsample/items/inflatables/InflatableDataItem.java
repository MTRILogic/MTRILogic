package com.mtrilogic.mtrilogicsample.items.inflatables;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.BindingInflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemDataBinding;
import com.mtrilogic.mtrilogicsample.models.DataModel;

@SuppressWarnings({"unused"})
public class InflatableDataItem extends BindingInflatable<DataModel, InflatableItemListener, ItemDataBinding> {
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableDataItem(@NonNull ItemDataBinding binding, @NonNull InflatableItemListener listener){
        super(binding, listener);
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
    public DataModel getModelFromModelable(@NonNull Modelable modelable) {
        return (DataModel) modelable;
    }

    @Override
    public void onBindModel(){
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, position));
    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Item [" + position + "] set to " + checked);
        }
    }
}
