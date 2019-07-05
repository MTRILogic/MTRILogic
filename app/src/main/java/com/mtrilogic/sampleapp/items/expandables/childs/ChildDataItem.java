package com.mtrilogic.sampleapp.items.expandables.childs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.DataModel;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class ChildDataItem extends ExpandableChild implements View.OnClickListener{
    private static final String TAG = "ChildDataItem";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int groupPosition, childPosition;
    private boolean lastChild;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public ChildDataItem(Context context, int resource){
        this(context,(ExpandableAdapterListener)context, resource);
    }

    public ChildDataItem(Context context, ExpandableAdapterListener listener, int resource){
        super(context, listener, resource);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public View getInflatableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false);
        chkItem = view.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, int childPosition, boolean lastChild){
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
        ExpandableAdapter adapter = getListener().getExpandableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                getListener().onMakeToast("Item[" + groupPosition + "," + childPosition + "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.removeChildModelable(groupPosition, childPosition)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
