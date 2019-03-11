package com.mtrilogic.sampleapp.items.expandables.childs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mtrilogic.abstracts.Expandable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.ChildModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings("unused")
public class ChildItem extends Expandable implements View.OnClickListener{
    private static final String TAG = "ChildItemTAG";
    private TextView lblTitle, lblContent;
    private CheckBox chkChild;
    private SquareImageView ivwIcon;
    private ChildModel model;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public ChildItem(Context context){
        this(context,(ExpandableAdapterListener)context);
    }

    public ChildItem(Context context, ExpandableAdapterListener listener){
        super(context,listener);
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public View getExpandableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ivwIcon = view.findViewById(R.id.ivw_icon);
        chkChild = view.findViewById(R.id.chk_child);
        chkChild.setOnClickListener(this);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (ChildModel)modelable;
        lblTitle.setText(model.getTitle());
        lblTitle.setTextColor(isLastChild() ? Color.RED : Color.BLACK);
        lblContent.setText(model.getContent());
        chkChild.setChecked(model.isChecked());
        ivwIcon.setImageResource(model.getIcon());
    }

    @Override
    public int getLayoutResource(){
        return R.layout.item_child;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.chk_child:
                boolean checked = chkChild.isChecked();
                model.setChecked(checked);
                ExpandableAdapterListener listener = getListener();
                listener.getExpandableAdapter().notifyDataSetChanged();
                listener.onMakeToast("Item[" + model.getGroupPosition() + "," + model.getChildPosition() + "] set to " + checked);
        }
    }
}
