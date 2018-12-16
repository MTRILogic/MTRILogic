package com.mtri.mtrilogic.items.inflatables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtri.abstracts.Inflatable;
import com.mtri.mtrilogic.R;
import com.mtri.mtrilogic.models.DataModel;
import com.mtri.views.SquareImageView;

public class DataItem extends Inflatable<DataModel>{

    public DataItem(long id, DataModel model){
        super(model,id);
    }

    @Override
    public View getInflatableView(View view, ViewGroup parent, Context context){
        ViewHolder holder = null;
        if(view != null){
            Object tag = view.getTag();
            if(tag instanceof ViewHolder){
                holder = (ViewHolder)tag;
            }
        }
        if(holder == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.lblTitle.setText(model.getTitle());
        holder.lblContent.setText(model.getContent());
        holder.ivwIcon.setImageResource(model.getIcon());
        return view;
    }

    private static final class ViewHolder{
        private TextView lblTitle, lblContent;
        private SquareImageView ivwIcon;

        private ViewHolder(View view){
            lblTitle = view.findViewById(R.id.lbl_title);
            lblContent = view.findViewById(R.id.lbl_content);
            ivwIcon = view.findViewById(R.id.ivw_icon);
        }
    }
}
