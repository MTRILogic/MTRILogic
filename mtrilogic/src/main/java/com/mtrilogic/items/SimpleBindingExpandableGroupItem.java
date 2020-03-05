package com.mtrilogic.items;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.BindingExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.models.SimpleModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleBinding;

@SuppressWarnings("unused")
public class SimpleBindingExpandableGroupItem extends BindingExpandableGroup<SimpleModel, ItemSimpleBinding> {

    public SimpleBindingExpandableGroupItem(@NonNull ItemSimpleBinding binding, @NonNull ExpandableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    protected SimpleModel getModelFromModelable(@NonNull Modelable modelable) {
        return (SimpleModel) modelable;
    }

    @Override
    public void onBindHolder() {
        TextView lblText = binding.lblText;
        lblText.setBackgroundColor(model.getBackColor());
        lblText.setTextColor(model.getColor());
        lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleModel model) {

    }
}
