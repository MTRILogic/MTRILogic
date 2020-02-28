package com.mtrilogic.mtrilogicsample.items.expandables.groups;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemGroupBinding;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;

import java.util.ArrayList;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class GroupDataItem extends ExpandableGroup<DataModel, ItemGroupBinding> {
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public GroupDataItem(@NonNull ItemGroupBinding binding, @NonNull ExpandableAdapterListener listener){
        super(binding, listener);
        chkItem = binding.chkItem;
        chkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChecked();
            }
        });
        chkItem.setFocusable(false);
        lblTitle = binding.lblTitle;
        lblContent = binding.lblContent;
        ImageButton btnAddData = binding.btnAddData;
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewModelable(ItemChildType.DATA);
            }
        });
        btnAddData.setFocusable(false);
        ImageButton btnAddImage = binding.btnAddImage;
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewModelable(ItemChildType.IMAGE);
            }
        });
        btnAddImage.setFocusable(false);
        ImageButton btnDelete = binding.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        btnDelete.setFocusable(false);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onChanged(DataModel dataModel) {

    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

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
        lblContent.setText(context.getString(R.string.content_item, groupPosition));
    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        if (childListable != null) {
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            ArrayList<Modelable> childModelableList = childListable.getModelableList();
            for (Modelable childModelable : childModelableList) {
                DataModel model = (DataModel) childModelable;
                model.setChecked(checked);
            }
            listener.getExpandableAdapter().notifyDataSetChanged();
        }
    }

    private void addNewModelable(int viewType){
        if (childListable != null){
            long idx = childListable.getIdx();
            Context context = itemView.getContext();
            Modelable modelable = Utils.getNewModelable(context, viewType, idx, chkItem.isChecked());
            if (modelable != null) {
                addNewChildModelable(modelable, idx);
            }
        }
    }
}
