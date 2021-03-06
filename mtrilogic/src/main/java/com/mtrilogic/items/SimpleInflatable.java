package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.models.SimpleModel2;

@SuppressWarnings("unused")
public class SimpleInflatable extends Inflatable<SimpleModel2, InflatableItemListener> {
    private TextView lblText1, lblText2;

    public SimpleInflatable(@NonNull View itemView, @NonNull InflatableItemListener listener) {
        super(itemView, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    public SimpleInflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull InflatableItemListener listener) {
        super(inflater, resource, parent, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    @Override
    public SimpleModel2 getModelFromModelable(@NonNull Modelable modelable) {
        return (SimpleModel2) modelable;
    }

    @Override
    public void onBindModel() {
        lblText1.setText(model.getText1());
        if (lblText2 != null){
            lblText2.setText(model.getText2());
        }
    }
}
