package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.interfaces.ExpandableItemListener;
import com.mtrilogic.mtrilogic.databinding.ItemDefaultBinding;
import com.mtrilogic.mtrilogic.models.DefaultModel;
import com.mtrilogic.mtrilogic.R;

@SuppressWarnings("unused")
public class DefaultExpandableChild extends ExpandableChild<DefaultModel> {

    private ItemDefaultBinding binding;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultExpandableChild(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull ExpandableItemListener listener) {
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
        binding.lblTitle.setText(itemView.getContext().getString(R.string.default_child, model.getItemId(), childPosition));
    }
}
