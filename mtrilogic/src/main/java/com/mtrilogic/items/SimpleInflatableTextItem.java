package com.mtrilogic.items;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.models.SimpleTextModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleTextBinding;

@SuppressWarnings("unused")
public class SimpleInflatableTextItem extends Inflatable<SimpleTextModel, ItemSimpleTextBinding> {

    public SimpleInflatableTextItem(@NonNull ItemSimpleTextBinding binding, @NonNull InflatableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    public void onBindHolder(Modelable modelable, int position) {
        bindModel((SimpleTextModel) modelable, position);
        binding.lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleTextModel simpleTextModel) {

    }
}
