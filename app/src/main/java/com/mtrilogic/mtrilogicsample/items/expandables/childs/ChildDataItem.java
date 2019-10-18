package com.mtrilogic.mtrilogicsample.items.expandables.childs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class ChildDataItem extends ExpandableChild implements View.OnClickListener{
    private static final String TAG = "ChildDataItem";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int groupPosition, childPosition;
    private boolean lastChild;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ChildDataItem(Context context, int resource, ViewGroup parent){
        this(context, resource, parent,(ExpandableAdapterListener)context);
    }

    public ChildDataItem(Context context, int resource, ViewGroup parent,
                         ExpandableAdapterListener listener){
        super(context, resource, parent, listener);
        chkItem = itemView.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, int childPosition,
                             boolean lastChild){
        model = (DataModel)modelable;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        chkItem.setChecked(model.isChecked());
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, childPosition));
    }

    @Override
    public void onClick(View view){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                listener.onMakeToast("Item[" + groupPosition + "," + childPosition +
                        "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.deleteChildModelable(adapter.getGroup(groupPosition), model)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
