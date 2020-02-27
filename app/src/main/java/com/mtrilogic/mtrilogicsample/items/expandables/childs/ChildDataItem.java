package com.mtrilogic.mtrilogicsample.items.expandables.childs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ChildDataItem(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull ExpandableAdapterListener listener){
        super(ItemChildDataBinding.inflate(inflater, parent, false), listener);
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

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onChanged(DataModel dataModel) {

    }

// ++++++++++++++++| PROTECTED OVERRIDE METHODS |+++++++++++++++++++++++++++++++++++++++++++++++++++

    @NonNull
    @Override
    protected DataModel getModel(@NonNull Modelable modelable) {
        return (DataModel) modelable;
    }

    @Override
    protected void onBindHolder(){
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, childPosition));
    }

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
