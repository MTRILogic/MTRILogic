package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.models.DefaultModel;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class DefaultGroupItem extends ExpandableGroup<DefaultModel> {
    private TextView lblTitle;

    public DefaultGroupItem(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull ExpandableItemListener listener) {
        super(inflater.inflate(R.layout.item_default, parent, false), listener);
    }

    @Override
    public DefaultModel getModelFromModelable(@NonNull Modelable modelable) {
        return (DefaultModel) modelable;
    }

    @Override
    public void onBindItemView() {
        lblTitle = itemView.findViewById(R.id.lblTitle);
    }

    @Override
    public void onBindModel() {
        lblTitle.setText(itemView.getContext().getString(R.string.default_group, model.getItemId(), groupPosition));
    }
}
