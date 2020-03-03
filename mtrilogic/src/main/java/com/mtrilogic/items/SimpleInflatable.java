package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.models.SimpleModel2;

@SuppressWarnings("unused")
public class SimpleInflatable extends Inflatable<SimpleModel2> {
    private TextView lblText1, lblText2;

    public SimpleInflatable(@NonNull View itemView, @NonNull InflatableAdapterListener listener) {
        super(itemView, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    public SimpleInflatable(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull InflatableAdapterListener listener) {
        super(inflater, resource, parent, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
        lblText2 = itemView.findViewById(android.R.id.text2);
    }

    @Override
    protected void onBindHolder(@NonNull Modelable modelable) {
        model = (SimpleModel2) modelable;
        lblText1.setText(model.getText1());
        if (lblText2 != null){
            lblText2.setText(model.getText2());
        }
    }

    @Override
    public void onChanged(SimpleModel2 model) {

    }
}
