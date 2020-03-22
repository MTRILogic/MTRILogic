package com.mtrilogic.items;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.BindingRecyclable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.RecyclableItemListener;
import com.mtrilogic.models.SimpleModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleBinding;

@SuppressWarnings("unused")
public class SimpleBindingRecyclableItem extends BindingRecyclable<SimpleModel,
        RecyclableItemListener, ItemSimpleBinding> {

    public SimpleBindingRecyclableItem(@NonNull ItemSimpleBinding binding, @NonNull RecyclableItemListener listener) {
        super(binding, listener);
    }

    @Override
    protected SimpleModel getModelFromModelable(@NonNull Modelable modelable) {
        return (SimpleModel) modelable;
    }

    @Override
    public void onBindModel() {
        TextView lblText = binding.lblText;
        lblText.setBackgroundColor(model.getBackColor());
        lblText.setTextColor(model.getColor());
        lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleModel model) {

    }
}
