package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.interfaces.InflatableItemListener;
import com.mtrilogic.mtrilogic.databinding.ItemDefaultBinding;
import com.mtrilogic.mtrilogic.models.DefaultModel;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class DefaultInflatable extends Inflatable<DefaultModel> {

    private ItemDefaultBinding binding;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultInflatable(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull InflatableItemListener listener) {
        super(DefaultModel.class, ItemDefaultBinding.inflate(inflater, parent, false).getRoot(), listener);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onBindItemView() {
        binding = ItemDefaultBinding.bind(itemView);
    }

    @Override
    public void onBindModel() {
        binding.lblTitle.setText(itemView.getContext().getString(R.string.default_item, model.getItemId(), position));
    }
}
