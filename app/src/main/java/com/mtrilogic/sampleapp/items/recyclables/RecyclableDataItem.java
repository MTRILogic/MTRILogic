package com.mtrilogic.sampleapp.items.recyclables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.views.SquareImageView;

public class RecyclableDataItem extends Recyclable implements View.OnClickListener{
    private TextView lblTitle, lblContent;
    private SquareImageView ivwIcon;
    private DataModel model;

    @SuppressWarnings("unused")
    public RecyclableDataItem(Context context){
        this(context,(RecyclableAdapterListener)context);
    }

    @SuppressWarnings("WeakerAccess")
    public RecyclableDataItem(Context context, RecyclableAdapterListener listener){
        super(new View(context),context,listener);
    }

    private RecyclableDataItem(View view, Context context, RecyclableAdapterListener listener){
        super(view,context,listener);
        itemView.setOnClickListener(this);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ivwIcon = view.findViewById(R.id.ivw_icon);
        ivwIcon.setOnClickListener(this);
    }

    @Override
    public Recyclable getRecyclableHolder(ViewGroup parent){
        Context context = getContext();
        View view = LayoutInflater.from(context).inflate(getLayoutResource(),parent,false);
        return new RecyclableDataItem(view,context,getListener());
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (DataModel)modelable;
        lblTitle.setText(model.getTitle());
        lblContent.setText(model.getContent());
        ivwIcon.setImageResource(model.getIcon());
    }

    @Override
    public int getLayoutResource(){
        return R.layout.item_data;
    }

    @Override
    public void onClick(View view){
        RecyclableAdapterListener listener = getListener();
        int id = view.getId();
        switch(id){
            case R.id.ivw_icon:
                listener.onMakeToast("Icon [" + model.getPosition() + "] clicked");
                break;
            default:
                listener.onMakeToast("Item [" + getLayoutPosition() + "] clicked");
                break;
        }
    }
}
