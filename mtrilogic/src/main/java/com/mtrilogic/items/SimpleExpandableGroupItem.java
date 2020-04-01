package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.models.SimpleModel1;

@SuppressWarnings("unused")
public class SimpleExpandableGroupItem extends ExpandableGroup<SimpleModel1, ExpandableItemListener> {
    private TextView lblText1;

    public SimpleExpandableGroupItem(@NonNull View itemView, @NonNull ExpandableItemListener listener) {
        super(itemView, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
    }

    public SimpleExpandableGroupItem(@NonNull LayoutInflater inflater, int resource, @NonNull ViewGroup parent, @NonNull ExpandableItemListener listener) {
        super(inflater, resource, parent, listener);
        lblText1 = itemView.findViewById(android.R.id.text1);
    }

    @Override
    public SimpleModel1 getModelFromModelable(@NonNull Modelable modelable) {
        return (SimpleModel1) modelable;
    }

    @Override
    public void onBindModel() {
        lblText1.setText(model.getText1());
    }
}
