package com.mtrilogic.sampleapp.items.expandables.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.sampleapp.types.ChildType;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class GroupDataItem extends ExpandableGroup implements View.OnClickListener{
    private static final String TAG = "GroupDataItem";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private DataModel model;
    private int groupPosition;
    private boolean expanded;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public GroupDataItem(Context context, int resource){
        this(context,(ExpandableAdapterListener)context, resource);
    }

    public GroupDataItem(Context context, ExpandableAdapterListener listener, int resource){
        super(context,listener, resource);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public View getInflatableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(), parent,false);
        chkItem = view.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        chkItem.setFocusable(false);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnAddData = view.findViewById(R.id.btn_addData);
        btnAddData.setOnClickListener(this);
        btnAddData.setFocusable(false);
        ImageButton btnAddImage = view.findViewById(R.id.btn_addImage);
        btnAddImage.setOnClickListener(this);
        btnAddImage.setFocusable(false);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        btnDelete.setFocusable(false);
        view.setTag(this);
        return view;
    }

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
        ExpandableAdapter adapter = getListener().getExpandableAdapter();
        int id = view.getId();
        if(id == R.id.btn_delete){
            if(adapter.removeGroupModelable(model)){
                adapter.notifyDataSetChanged();
            }
        }else {
            Listable<Modelable> childModelableList = adapter.getChildModelableList(groupPosition);
            if(id == R.id.chk_item){
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                for(Modelable childModelable : childModelableList.list){
                    DataModel model  = (DataModel)childModelable;
                    model.setChecked(checked);
                }
                adapter.notifyDataSetChanged();
            }else {
                long idx = childModelableList.idx;
                DataModel model = null;
                switch(id){
                    case R.id.btn_addData:
                        model = new DataModel(idx, ChildType.DATA);
                        break;
                    case R.id.btn_addImage:
                        model = getImageModel(idx);
                        break;
                }
                if(model != null && adapter.addChildModelable(groupPosition, model)){
                    adapter.notifyDataSetChanged();
                    childModelableList.idx++;
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
