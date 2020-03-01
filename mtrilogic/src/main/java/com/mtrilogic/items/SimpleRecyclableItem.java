package com.mtrilogic.items;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.models.SimpleModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleBinding;

@SuppressWarnings("unused")
public class SimpleRecyclableItem extends Recyclable<SimpleModel, ItemSimpleBinding> {

    public SimpleRecyclableItem(@NonNull ItemSimpleBinding binding, @NonNull RecyclableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    public void onBindHolder(@NonNull Modelable modelable) {
        model = (SimpleModel) modelable;
        TextView lblText = binding.lblText;
        lblText.setBackgroundColor(model.getBackColor());
        lblText.setTextColor(model.getColor());
        lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleModel model) {

    }
}
