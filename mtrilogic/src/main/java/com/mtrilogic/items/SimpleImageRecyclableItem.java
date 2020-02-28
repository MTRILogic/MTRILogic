package com.mtrilogic.items;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.models.SimpleImageModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleImageBinding;

@SuppressWarnings("unused")
public class SimpleImageRecyclableItem extends Recyclable<SimpleImageModel, ItemSimpleImageBinding> {

    public SimpleImageRecyclableItem(@NonNull ItemSimpleImageBinding binding, @NonNull RecyclableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    public void onBindHolder(Modelable modelable, int position) {
        bindModel((SimpleImageModel) modelable, position);
        binding.ivwImage.setImageResource(model.getImage());
    }

    @Override
    public void onChanged(SimpleImageModel simpleImageModel) {

    }
}
