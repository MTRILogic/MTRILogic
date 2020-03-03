package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.models.SimpleModel1;

@SuppressWarnings("unused")
public class SimpleExpandableGroupItem extends ExpandableGroup<SimpleModel1> {
    private TextView lblText1;

    public SimpleExpandableGroupItem(@NonNull View itemView, @NonNull ExpandableAdapterListener listener) {
        super(itemView, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
    }

    public SimpleExpandableGroupItem(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull ExpandableAdapterListener listener) {
        super(inflater, resource, parent, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
    }

    @Override
    protected void onBindHolder(@NonNull Modelable modelable) {
        model = (SimpleModel1) modelable;
        lblText1.setText(model.getText1());
    }

    @Override
    public void onChanged(SimpleModel1 model) {

    }
}
