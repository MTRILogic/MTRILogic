package com.mtrilogic.mtrilogicsample.items.expandables.groups;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

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
    private static final String TAG = "GroupDataItemTAG";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public GroupDataItem(Context context, int resource, ViewGroup parent){
        this(context, resource, parent,(ExpandableAdapterListener)context);
    }

    public GroupDataItem(Context context, int resource, ViewGroup parent,
                         ExpandableAdapterListener listener){
        super(context, resource, parent, listener);
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

    @Override
    protected DataModel getModel(Modelable modelable) {
        return (DataModel) modelable;
    }

    @Override
    protected void onBindHolder(){
        chkItem.setChecked(model.isChecked());
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
            adapter.notifyDataSetChanged();
        }
    }

    private void addNewModelable(int viewType){
        if (childListable != null){
            long idx = childListable.getIdx();
            Modelable modelable = Utils.getNewModelable(context, viewType, idx, chkItem.isChecked());
            addNewChildModelable(modelable, idx);
        }
    }
}
