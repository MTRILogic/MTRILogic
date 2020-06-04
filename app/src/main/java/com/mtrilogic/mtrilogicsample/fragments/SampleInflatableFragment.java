package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.BindingInflatableFragment;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.FragmentInflatableBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemDataBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemImageBinding;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableImageItem;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableDataItem;
import com.mtrilogic.mtrilogicsample.pages.SampleListPage;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;
import com.mtrilogic.views.InflatableView;

@SuppressWarnings("unused")
public class SampleInflatableFragment extends BindingInflatableFragment<SampleListPage,
        FragmentListener, FragmentInflatableBinding> {
    private TextView lblContent;

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected FragmentListener getListenerFromContext(@NonNull Context context) {
        return (FragmentListener) context;
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInflatableBinding.inflate(inflater, container, false);
        InflatableView lvwItems = binding.lvwItems;
        TextView lblTitle = binding.lblTitle;

        Context context = getContext();
        if (context != null && page != null){
            bindInflatable(context, lvwItems, page, ItemChildType.COUNT);
            lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        }

        lblContent = binding.lblContent;
        ImageButton btnAddData = binding.btnAddData;
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addModelable(ItemChildType.DATA);
            }
        });
        ImageButton btnAddImage = binding.btnAddImage;
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addModelable(ItemChildType.IMAGE);
            }
        });
        ImageButton btnDelete = binding.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onNewPosition(int position) {
        lblContent.setText(getString(R.string.content_item, position));
    }

    @Override
    public Inflatable<?, ?> getInflatable(int viewType, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        Context context = getContext();
        switch(viewType){
            case ItemChildType.DATA:
                return new InflatableDataItem(ItemDataBinding.inflate(inflater, parent, false), this);
            case ItemChildType.IMAGE:
                return new InflatableImageItem(ItemImageBinding.inflate(inflater, parent, false), this);
        }
        return null;
    }

    // ================< PRIVATE METHODS >==========================================================

    private void addModelable(int viewType){
        Context context = getContext();
        if (context != null && page != null){
            Listable<Modelable> listable = page.getListable();
            if (listable != null){
                Modelable modelable = Utils.getNewModelable(viewType, context);
                if (modelable != null && listable.append(modelable)){
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
