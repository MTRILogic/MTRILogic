package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogic.models.DefaultModel;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class DefaultInflatable extends Inflatable<DefaultModel> {
    private TextView lblTitle;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultInflatable(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull InflatableItemListener listener) {
        super(DefaultModel.class, inflater.inflate(R.layout.item_default, parent, false), listener);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onBindItemView() {
        lblTitle = itemView.findViewById(R.id.lblTitle);
    }

    @Override
    public void onBindModel() {
        lblTitle.setText(itemView.getContext().getString(R.string.default_item, model.getItemId(), position));
    }
}
