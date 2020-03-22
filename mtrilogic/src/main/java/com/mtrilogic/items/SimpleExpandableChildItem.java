package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.models.SimpleModel2;

@SuppressWarnings("unused")
public class SimpleExpandableChildItem extends ExpandableChild<SimpleModel2, ExpandableItemListener> {
    private TextView lblText1, lblText2;

    public SimpleExpandableChildItem(@NonNull View itemView, @NonNull ExpandableItemListener listener) {
        super(itemView, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    public SimpleExpandableChildItem(@NonNull LayoutInflater inflater, int resource,
                                     @NonNull ViewGroup parent, @NonNull ExpandableItemListener listener) {
        super(inflater, resource, parent, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    @Override
    protected SimpleModel2 getModelFromModelable(@NonNull Modelable modelable) {
        return (SimpleModel2) modelable;
    }

    @Override
    protected void onBindModel() {
        lblText1.setText(model.getText1());
        if (lblText2 != null){
            lblText2.setText(model.getText2());
        }
    }

    @Override
    public void onChanged(SimpleModel2 model) {

    }
}
