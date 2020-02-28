package com.mtrilogic.items;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.models.SimpleTextModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleTextBinding;

@SuppressWarnings("unused")
public class SimpleRecyclableTextItem extends Recyclable<SimpleTextModel, ItemSimpleTextBinding> {

    public SimpleRecyclableTextItem(@NonNull ItemSimpleTextBinding binding, @NonNull RecyclableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    public void onBindHolder(Modelable modelable, int position) {
        bindModel((SimpleTextModel) modelable, position);
        binding.lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleTextModel model) {

    }
}
