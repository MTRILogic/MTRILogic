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

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.interfaces.InflatableListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.extras.Utils;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableImageItem;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableDataItem;
import com.mtrilogic.mtrilogicsample.pages.InflatablePage;
import com.mtrilogic.mtrilogicsample.types.ChildType;
import com.mtrilogic.views.InflatableView;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class InflatableFragment extends Fragmentable<InflatablePage> implements InflatableListener,
        InflatableAdapterListener {
    private static final String TAG = "InflatableFragmentTAG";
    private InflatableAdapter adapter;
    private InflatableView lvwItems;
    private TextView lblContent;

// PROTECTED OVERRIDE METHODS |*********************************************************************

    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        ArrayList<Modelable> modelableList = page.getModelableList();
        adapter = new InflatableAdapter(this, modelableList, ChildType.COUNT);
        lvwItems = view.findViewById(R.id.lvw_items);
        lvwItems.setAdapter(adapter);
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

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (args != null && lvwItems != null){
            lvwItems.restoreFromState(args);
        }
    }

    @Override
    public void onPause() {
        if (args != null && lvwItems != null) {
            lvwItems.saveToState(args);
        }
        super.onPause();
    }

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
    public InflatableAdapter getInflatableAdapter(){
        return adapter;
    }

    @Override
    public InflatableView getInflatableView() {
        return lvwItems;
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
