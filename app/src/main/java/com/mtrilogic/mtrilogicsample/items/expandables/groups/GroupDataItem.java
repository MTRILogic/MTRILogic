package com.mtrilogic.mtrilogicsample.items.expandables.groups;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.mtrilogicsample.types.ChildType;

import java.util.ArrayList;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class GroupDataItem extends ExpandableGroup implements View.OnClickListener{
    private static final String TAG = "GroupDataItem";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int groupPosition;
    private boolean expanded;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public GroupDataItem(Context context, int resource, ViewGroup parent){
        this(context, resource, parent,(ExpandableAdapterListener)context);
    }

    public GroupDataItem(Context context, int resource, ViewGroup parent,
                         ExpandableAdapterListener listener){
        super(context, resource, parent, listener);
        chkItem = itemView.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        chkItem.setFocusable(false);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ImageButton btnAddData = itemView.findViewById(R.id.btn_addData);
        btnAddData.setOnClickListener(this);
        btnAddData.setFocusable(false);
        ImageButton btnAddImage = itemView.findViewById(R.id.btn_addImage);
        btnAddImage.setOnClickListener(this);
        btnAddImage.setFocusable(false);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        btnDelete.setFocusable(false);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, boolean expanded){
        model = (DataModel)modelable;
        this.groupPosition = groupPosition;
        this.expanded = expanded;
        chkItem.setChecked(model.isChecked());
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, groupPosition));
    }

    @Override
    public void onClick(View view){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        int id = view.getId();
        if(id == R.id.btn_delete){
            if(adapter.deleteGroupModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }else {
            Modelable groupModelable = adapter.getGroup(groupPosition);
            Listable childListable = adapter.getChildListable(groupModelable);
            boolean checked = chkItem.isChecked();
            if(id == R.id.chk_item){
                model.setChecked(checked);
                ArrayList<Modelable> childModelableList = childListable.getModelableList();
                for(Modelable childModelable : childModelableList){
                    DataModel model  = (DataModel)childModelable;
                    model.setChecked(checked);
                }
                adapter.notifyDataSetChanged();
            }else {
                long idx = childListable.getIdx();
                DataModel model = null;
                switch(id){
                    case R.id.btn_addData:
                        model = new DataModel(idx, ChildType.DATA);
                        break;
                    case R.id.btn_addImage:
                        model = getImageModel(idx);
                        break;
                }
                if(model != null && adapter.appendChildModelable(groupModelable, model)){
                    model.setChecked(checked);
                    adapter.notifyDataSetChanged();
                    childListable.setIdx(++idx);
                }
            }
        }
    }

    private String[] getLinks(){
        Context context = getContext();
        if(context != null){
            return context.getResources().getStringArray(R.array.links);
        }else {
            return new String[5];
        }
    }

    private ImageModel getImageModel(long idx){
        String[] links = getLinks();
        ImageModel model = new ImageModel(idx, ChildType.IMAGE);
        model.setImageLink(links[getRandomInt()]);
        model.setRating(getRandomInt());
        return model;
    }

    private int getRandomInt(){
        return (int)(Math.random() * 4) + 1;
    }
}
