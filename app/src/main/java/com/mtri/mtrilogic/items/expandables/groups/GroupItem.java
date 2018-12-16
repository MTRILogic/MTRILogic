package com.mtri.mtrilogic.items.expandables.groups;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mtri.abstracts.ExpandableChild;
import com.mtri.abstracts.ExpandableGroup;
import com.mtri.interfaces.OnNotifyDataSetChangedListener;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.models.ChildModel;
import com.mtri.mtrilogic.models.GroupModel;

public class GroupItem extends ExpandableGroup implements View.OnClickListener{
    private static final String TAG = "GroupItemTAG";
    private GroupModel model;

    public GroupItem(OnNotifyDataSetChangedListener listener, long id, GroupModel model){
        super(listener,id);
        this.model = model;
    }

    @Override
    public View getExpandableView(boolean b, View view, ViewGroup parent, Context context){
        Log.d(TAG, "getExpandableView: " + b);
        ViewHolder holder = null;
        if(view != null){
            Object tag = view.getTag();
            if(tag instanceof ViewHolder){
                holder = (ViewHolder)tag;
            }
        }
        if(holder == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_group,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.lblTitle.setText(model.getTitle());
        holder.chkGroup.setChecked(model.isChecked());
        holder.chkGroup.setOnClickListener(this);
        return view;
    }

    @Override
    public GroupModel getModel(){
        return model;
    }

    @Override
    public void onClick(View v){
        boolean checked = ((CheckBox)v).isChecked();
        model.setChecked(checked);
        for(ExpandableChild child : getChildList()){
            ((ChildModel)child.getModel()).setChecked(checked);
        }
        listener.onNotifyDataSetChanged();
    }

    private static final class ViewHolder{
        private TextView lblTitle;
        private CheckBox chkGroup;

        private ViewHolder(View view){
            lblTitle = view.findViewById(R.id.lbl_title);
            chkGroup = view.findViewById(R.id.chk_group);
        }
    }
}
