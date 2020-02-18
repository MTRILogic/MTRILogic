package com.mtrilogic.mtrilogicsample.items.expandables.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.types.ChildType;

import java.util.ArrayList;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class GroupDataItem extends ExpandableGroup<DataModel> {
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public GroupDataItem(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent,
                         @NonNull ExpandableAdapterListener listener){
        super(inflater, resource, parent, listener);
        chkItem = itemView.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChecked();
            }
        });
        chkItem.setFocusable(false);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ImageButton btnAddData = itemView.findViewById(R.id.btn_addData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewModelable(ChildType.DATA);
            }
        });
        btnAddData.setFocusable(false);
        ImageButton btnAddImage = itemView.findViewById(R.id.btn_addImage);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewModelable(ChildType.IMAGE);
            }
        });
        btnAddImage.setFocusable(false);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        btnDelete.setFocusable(false);
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
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, groupPosition));
    }

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
            Context context = getContext();
            Modelable modelable = Utils.getNewModelable(context, viewType, idx, chkItem.isChecked());
            if (modelable != null) {
                addNewChildModelable(modelable, idx);
            }
        }
    }
}
