package com.mtrilogic.sampleapp.items.inflatables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.DataModel;
import com.mtrilogic.views.SquareImageView;

public class InflatableDataItem extends Inflatable implements View.OnClickListener{
    private TextView lblTitle, lblContent;
    private SquareImageView ivwIcon;
    private DataModel model;

    @SuppressWarnings("unused")
    public InflatableDataItem(Context context){
        this(context,(InflatableAdapterListener)context);
    }

    @SuppressWarnings("WeakerAccess")
    public InflatableDataItem(Context context, InflatableAdapterListener listener){
        super(context,listener);
    }

    @Override
    public View getInflatableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ivwIcon = view.findViewById(R.id.ivw_icon);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (DataModel)modelable;
        lblTitle.setText(model.getTitle());
        lblContent.setText(model.getContent());
        ivwIcon.setImageResource(model.getIcon());
        ivwIcon.setOnClickListener(this);
    }

    @Override
    public int getLayoutResource(){
        return R.layout.item_data;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.ivw_icon:
                getListener().onMakeToast("Icon [" + model.getPosition() + "] clicked");
                break;
        }
    }
}
