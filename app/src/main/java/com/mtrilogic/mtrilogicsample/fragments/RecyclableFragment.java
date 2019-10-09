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

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.items.recyclables.RecyclableDataItem;
import com.mtrilogic.mtrilogicsample.items.recyclables.RecyclableImageItem;
import com.mtrilogic.mtrilogicsample.models.DataModel;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.mtrilogicsample.pages.RecyclablePage;
import com.mtrilogic.mtrilogicsample.types.ChildType;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class RecyclableFragment extends Fragmentable implements View.OnClickListener,
        RecyclableListener, RecyclableAdapterListener{
    private static final String TAG = "RecyclableFragmentTAG";
    private static final String PAGE = "page";
    private FragmentableAdapterListener listener;
    private RecyclableAdapter adapter;
    private RecyclablePage page;
    private int position;

// ++++++++++++++++| PUBLIC STATIC METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static RecyclableFragment getInstance(RecyclablePage page){
        Bundle args = new Bundle();
        args.putParcelable(PAGE, page);
        RecyclableFragment fragment = new RecyclableFragment();
        fragment.setArguments(args);
        return fragment;
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof FragmentableAdapterListener){
            listener = (FragmentableAdapterListener)context;
        }
    }

    @Override
    public void onDetach(){
        listener = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            page = args.getParcelable(PAGE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recyclable,container,false);
        if (page != null) {
            position = listener.getFragmentableAdapter().getPaginablePosition(page);
            ArrayList<Modelable> modelables = page.getModelableList();
            adapter = new RecyclableAdapter(this, modelables);
            RecyclerView lvwItems = view.findViewById(R.id.lvw_items);
            lvwItems.setLayoutManager(new LinearLayoutManager(getContext()));
            lvwItems.setAdapter(adapter);
            TextView lblTitle = view.findViewById(R.id.lbl_title);
            lblTitle.setText(getString(R.string.title_item, page.getItemId()));
            TextView lblContent = view.findViewById(R.id.lbl_content);
            lblContent.setText(getString(R.string.content_item, position));
            ImageButton btnAddData = view.findViewById(R.id.btn_addData);
            btnAddData.setOnClickListener(this);
            ImageButton btnAddImage = view.findViewById(R.id.btn_addImage);
            btnAddImage.setOnClickListener(this);
            ImageButton btnDelete = view.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public Paginable getPaginable(){
        return page;
    }

    @Override
    public int getPosition(){
        return position;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.btn_delete){
            FragmentableAdapter adapter = listener.getFragmentableAdapter();
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
            if(model != null && adapter.addModelable(model)){
                adapter.notifyDataSetChanged();
                page.setIdx(++idx);
            }
        }
    }

    @Override
    public Recyclable getRecyclable(int viewType, ViewGroup parent){
        Context context = getContext();
        switch(viewType){
            case ChildType.DATA:
                return new RecyclableDataItem(context, R.layout.item_data, parent, this);
            case ChildType.IMAGE:
                return new RecyclableImageItem(context, R.layout.item_image, parent, this);
        }
        return null;
    }

    @Override
    public RecyclableAdapter getRecyclableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        listener.onMakeToast(line);
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
