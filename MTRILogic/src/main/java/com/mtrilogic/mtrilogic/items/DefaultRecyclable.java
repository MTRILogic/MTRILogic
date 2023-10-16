package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.databinding.ItemDefaultBinding;
import com.mtrilogic.mtrilogic.models.DefaultModel;

@SuppressWarnings("unused")
public class DefaultRecyclable extends Recyclable<DefaultModel> {

    private ItemDefaultBinding binding;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultRecyclable(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull RecyclableItemListener listener) {
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
