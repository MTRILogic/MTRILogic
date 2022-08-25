package com.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.models.DefaultModel;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class DefaultItem extends Inflatable<DefaultModel> {
    private TextView lblTitle;

    public DefaultItem(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull InflatableItemListener listener) {
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
        lblTitle.setText(model.getTitle());
    }
}
