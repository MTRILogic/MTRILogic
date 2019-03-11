package com.mtrilogic.sampleapp.items.expandables.groups;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mtrilogic.abstracts.Expandable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.ChildModel;
import com.mtrilogic.sampleapp.models.GroupModel;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class GroupItem extends Expandable implements View.OnClickListener{
    private static final String TAG = "GroupItemTAG";
    private TextView lblTitle;
    private CheckBox chkGroup;
    private GroupModel model;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public GroupItem(Context context){
        this(context,(ExpandableAdapterListener)context);
    }

    public GroupItem(Context context, ExpandableAdapterListener listener){
        super(context,listener);
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public View getExpandableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false);
        lblTitle = view.findViewById(R.id.lbl_title);
        chkGroup = view.findViewById(R.id.chk_group);
        chkGroup.setOnClickListener(this);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (GroupModel)modelable;
        lblTitle.setText(model.getTitle());
        lblTitle.setTextColor(isExpanded() ? Color.WHITE : Color.BLACK);
        chkGroup.setChecked(model.isChecked());
    }

    @Override
    public int getLayoutResource(){
        return R.layout.item_group;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.chk_group:
                boolean checked = chkGroup.isChecked();
                model.setChecked(checked);
                ExpandableAdapter adapter = getListener().getExpandableAdapter();
                ArrayList<Modelable> childModelables = adapter.getChildModelableList(model.getGroupPosition());
                for(Modelable modelable : childModelables){
                    ChildModel model = (ChildModel)modelable;
                    model.setChecked(checked);
                }
                adapter.notifyDataSetChanged();
        }
    }
}
