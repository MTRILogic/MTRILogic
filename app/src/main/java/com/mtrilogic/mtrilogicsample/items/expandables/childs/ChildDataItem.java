package com.mtrilogic.mtrilogicsample.items.expandables.childs;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildDataBinding;
import com.mtrilogic.mtrilogicsample.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class ChildDataItem extends ExpandableChild<DataModel, ItemChildDataBinding> {

    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ChildDataItem(@NonNull ItemChildDataBinding binding, @NonNull ExpandableAdapterListener listener){
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
    public void onBindHolder(@NonNull Modelable modelable){
        model = (DataModel) modelable;
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, childPosition));
    }

    @Override
    public void onChanged(DataModel dataModel) {

    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if (adapter != null) {
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Item [" + groupPosition + "," + childPosition +
                    "] set to " + checked);
        }
    }
}
