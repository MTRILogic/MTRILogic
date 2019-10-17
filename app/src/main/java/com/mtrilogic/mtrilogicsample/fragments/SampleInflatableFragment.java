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
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.abstracts.fragments.InflatableFragment;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableImageItem;
import com.mtrilogic.mtrilogicsample.items.inflatables.InflatableDataItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.mtrilogicsample.pages.SampleInflatablePage;
import com.mtrilogic.mtrilogicsample.types.ChildType;

@SuppressWarnings("unused")
public class SampleInflatableFragment extends InflatableFragment<SampleInflatablePage> implements
        View.OnClickListener {
    private TextView lblContent;

// PROTECTED OVERRIDE METHODS |*********************************************************************

    @Override
    protected View onCreateViewFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState,
                                        SampleInflatablePage page, int position) {
        View view = inflater.inflate(R.layout.fragment_inflatable,container,false);
        init(view, ChildType.COUNT, page);
        TextView lblTitle = view.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.title_item, page.getItemId()));
        lblContent = view.findViewById(R.id.lbl_content);
        onNewPosition(position);
        ImageButton btnAddData = view.findViewById(R.id.btn_addData);
        btnAddData.setOnClickListener(this);
        ImageButton btnAddImage = view.findViewById(R.id.btn_addImage);
        btnAddImage.setOnClickListener(this);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        return view;
    }

    @Override
    protected void onNewPosition(int position) {
        lblContent.setText(getString(R.string.content_item, position));
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
    public void onClick(View view){
        SampleInflatablePage page = getPage();
        int id = view.getId();
        if(id == R.id.btn_delete){
            FragmentableAdapter adapter = getListener().getFragmentableAdapter();
            if(adapter.removePaginable(page)){
                adapter.notifyDataSetChanged();
            }
        }else {
            long idx = page.getIdx();
            DataModel model = null;
            switch(id){
                case R.id.btn_addData:
                    model = new DataModel(idx, ChildType.DATA);
                    break;
                case R.id.btn_addImage:
                    model = getImageModel(idx);
                    break;
            }
            InflatableAdapter adapter = getInflatableAdapter();
            if(model != null && adapter.addModelable(model)){
                adapter.notifyDataSetChanged();
                page.setIdx(++idx);
            }
        }
    }

// ++++++++++++++++| PRIVATE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private String[] getLinks(){
        Context context = getContext();
        if(context != null){
            return context.getResources().getStringArray(R.array.links);
        }else {
            return new String[5];
        }
    }

    private ImageModel getImageModel(long idx){
        String[] links = getLinks();
        ImageModel model = new ImageModel(idx, ChildType.IMAGE);
        model.setImageLink(links[getRandomInt()]); // poner un random aqui
        model.setRating(getRandomInt());
        return model;
    }

    private int getRandomInt(){
        return (int)(Math.random() * 4) + 1;
    }
}
