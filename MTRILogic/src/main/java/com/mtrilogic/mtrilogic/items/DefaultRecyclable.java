package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.models.DefaultModel;

@SuppressWarnings("unused")
public class DefaultRecyclable extends Recyclable<DefaultModel> {

    private TextView lblTitle;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultRecyclable(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull RecyclableItemListener listener) {
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
