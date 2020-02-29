package com.mtrilogic.mtrilogicsample.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.abstracts.RecyclableFragment;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.FragmentRecyclableBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemDataBinding;
import com.mtrilogic.mtrilogicsample.databinding.ItemImageBinding;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.items.recyclables.RecyclableDataItem;
import com.mtrilogic.mtrilogicsample.items.recyclables.RecyclableImageItem;
import com.mtrilogic.mtrilogicsample.pages.SampleListPaginable;
import com.mtrilogic.mtrilogicsample.types.ItemChildType;

@SuppressWarnings("unused")
public class SampleRecyclableFragment extends RecyclableFragment<SampleListPaginable, FragmentRecyclableBinding>{

    private TextView lblContent;

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclableBinding.inflate(inflater, container, false);
        RecyclerView lvwItems = binding.lvwItems;
        bindRecyclable(lvwItems);
        TextView lblTitle = binding.lblTitle;
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
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
    public Recyclable getRecyclable(int viewType, LayoutInflater inflater, ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ItemChildType.DATA:
                return new RecyclableDataItem(ItemDataBinding.inflate(inflater, parent, false), this);
            case ItemChildType.IMAGE:
                return new RecyclableImageItem(ItemImageBinding.inflate(inflater, parent, false), this);
        }
        return null;
    }

    @Override
    public boolean onItemLongClick(View view, Modelable modelable, int position) {
        return false;
    }

    @Override
    public void onItemClick(View view, Modelable modelable, int position) {

    }

    // ================< PRIVATE METHODS >==========================================================

    private void addModelable(int viewType){
        Context context = getContext();
        long idx = page.getListable().getIdx();
        Modelable modelable = Utils.getNewModelable(context, viewType, idx, false);
        if (modelable != null && adapter.addModelable(modelable)){
            adapter.notifyDataSetChanged();
            page.getListable().setIdx(++idx);
        }
    }
}
