package com.mtrilogic.items;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.ExpandableGroup;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.models.SimpleModel;
import com.mtrilogic.mtrilogic.databinding.ItemSimpleBinding;

@SuppressWarnings("unused")
public class SimpleExpandableGroupItem extends ExpandableGroup<SimpleModel, ItemSimpleBinding> {

    public SimpleExpandableGroupItem(@NonNull ItemSimpleBinding binding, @NonNull ExpandableAdapterListener listener) {
        super(binding, listener);
    }

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, boolean expanded) {
        bindModel((SimpleModel) modelable, groupPosition, expanded);
        TextView lblText = binding.lblText;
        lblText.setBackgroundColor(model.getBackColor());
        lblText.setTextColor(model.getColor());
        lblText.setText(model.getText());
    }

    @Override
    public void onChanged(SimpleModel model) {

    }
}
