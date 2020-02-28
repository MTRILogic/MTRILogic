package com.mtrilogic.items;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.models.SimpleImageModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleImageBinding;

@SuppressWarnings("unused")
public class SimpleInflatableImageItem extends Inflatable<SimpleImageModel, ItemSimpleImageBinding> {

    public SimpleInflatableImageItem(@NonNull ItemSimpleImageBinding binding, @NonNull InflatableAdapterListener listener) {
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
