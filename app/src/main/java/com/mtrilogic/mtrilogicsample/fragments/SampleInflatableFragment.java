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
import com.mtrilogic.abstracts.InflatableFragment;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableImageItem;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableDataItem;
import com.mtrilogic.mtrilogicsample.pages.SampleListablePage;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings("unused")
public class SampleInflatableFragment extends InflatableFragment<SampleListablePage> {
    private TextView lblContent;

// PROTECTED OVERRIDE METHODS |*********************************************************************

    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        initInflatable(view, ChildType.COUNT);
        TextView lblTitle = view.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnAddData = view.findViewById(R.id.btn_addData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addModelable(ChildType.DATA);
            }
        });
        ImageButton btnAddImage = view.findViewById(R.id.btn_addImage);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addModelable(ChildType.IMAGE);
            }
        });
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        return view;
    }

    @Override
    protected void onNewPosition() {
        lblContent.setText(getString(R.string.content_item, position));
    }

    @Override
    protected void onInflatableCreated() {

    }

    // ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public Inflatable getInflatable(int viewType, ViewGroup parent) {
        Context context = getContext();
        switch(viewType){
            case ChildType.DATA:
                return new InflatableDataItem(context, R.layout.item_data, parent, this);
            case ChildType.IMAGE:
                return new InflatableImageItem(context, R.layout.item_image, parent, this);
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

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void addModelable(int viewType){
        long idx = page.getIdx();
        Modelable modelable = Utils.getNewModelable(context, viewType, idx, false);
        if (modelable != null && adapter.addModelable(modelable)){
            adapter.notifyDataSetChanged();
            page.setIdx(++idx);
        }
    }
}
