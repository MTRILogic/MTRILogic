package com.mtri.mtrilogic.items.expandables.childs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mtri.abstracts.ExpandableChild;
import com.mtri.interfaces.OnNotifyDataSetChangedListener;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.models.ChildModel;
import com.mtri.views.SquareImageView;

public class ChildItem extends ExpandableChild<ChildModel> implements View.OnClickListener{
    //private static final String TAG = "ChildItemTAG";

    public ChildItem(OnNotifyDataSetChangedListener listener, ChildModel model, long id, boolean selectable){
        super(listener,model,id,selectable);
    }

    @Override
    public View getExpandableView(boolean b, View view, ViewGroup parent, Context context){
        ViewHolder holder = null;
        if(view != null){
            Object tag = view.getTag();
            if(tag instanceof ViewHolder){
                holder = (ViewHolder)tag;
            }
        }
        if(holder == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_child,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.lblTitle.setText(model.getTitle());
        holder.lblContent.setText(model.getContent());
        holder.chkChild.setChecked(model.isChecked());
        holder.chkChild.setOnClickListener(this);
        holder.ivwIcon.setImageResource(model.getIcon());
        return view;
    }

    @Override
    public void onClick(View v){
        model.setChecked(((CheckBox)v).isChecked());
    }

    private static final class ViewHolder{
        private TextView lblTitle, lblContent;
        private CheckBox chkChild;
        private SquareImageView ivwIcon;

        private ViewHolder(View view){
            lblTitle = view.findViewById(R.id.lbl_title);
            lblContent = view.findViewById(R.id.lbl_content);
            chkChild = view.findViewById(R.id.chk_child);
            ivwIcon = view.findViewById(R.id.ivw_icon);
        }
    }
}
