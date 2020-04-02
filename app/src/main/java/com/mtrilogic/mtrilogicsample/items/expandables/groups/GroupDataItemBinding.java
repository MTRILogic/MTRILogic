package com.mtrilogic.mtrilogicsample.items.expandables.groups;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.BindingExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemGroupBinding;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;

import java.util.ArrayList;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class GroupDataItemBinding extends BindingExpandableGroup<DataModel, ExpandableItemListener, ItemGroupBinding> {
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public GroupDataItemBinding(@NonNull ItemGroupBinding binding, @NonNull ExpandableItemListener listener){
        super(binding, listener);
        chkItem = binding.chkItem;
        chkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateChecked(isChecked);
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
    public DataModel getModelFromModelable(@NonNull Modelable modelable) {
        return (DataModel) modelable;
    }

    @Override
    public void onBindModel(){
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, groupPosition));
    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(boolean checked){
        ExpandableAdapter adapter = this.listener.getExpandableAdapter();
        if (adapter != null){
            Listable<Modelable> childListable = adapter.getChildListable(model);
            if (childListable != null) {
                model.setChecked(checked);
                ArrayList<Modelable> childModelableList = childListable.getList();
                for (Modelable childModelable : childModelableList) {
                    DataModel model = (DataModel) childModelable;
                    model.setChecked(checked);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void addNewModelable(int viewType){
        Context context = itemView.getContext();
        DataModel model = (DataModel) Utils.getNewModelable(viewType, context);
        if (model != null){
            model.setChecked(this.model.isChecked());
            addChildModelable(model);
        }
    }
}
