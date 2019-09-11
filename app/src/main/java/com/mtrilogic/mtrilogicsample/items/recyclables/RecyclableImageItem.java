package com.mtrilogic.mtrilogicsample.items.recyclables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused"})
public class RecyclableImageItem extends Recyclable implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;
    private int position;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public RecyclableImageItem(Context context, int resource){
        this(context, (RecyclableAdapterListener)context, resource);
    }

    public RecyclableImageItem(Context context, RecyclableAdapterListener listener, int resource){
        super(new View(context), context, listener, resource);
    }

// ++++++++++++++++| PRIVATE CONSTRUCTORS |++++++++++++++++++++++++++++++++++++

    private RecyclableImageItem(View view, Context context, RecyclableAdapterListener listener, int resource){
        super(view, context, listener, resource);
        view.setOnClickListener(this);
        chkItem = view.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        ivwImage = view.findViewById(R.id.ivw_image);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public Recyclable getRecyclableHolder(ViewGroup parent){
        Context context = getContext();
        int resource = getLayoutResource();
        View view = LayoutInflater.from(context).inflate(resource, parent,false);
        return new RecyclableImageItem(view, context, getListener(), resource);
    }

    @Override
    public void onBindHolder(Modelable modelable, int position){
        model = (ImageModel)modelable;
        this.position = position;
        chkItem.setChecked(model.isChecked());
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, position));
        ratingBar.setRating(model.getRating());
        Glide.with(getContext())
            .load(model.getImageLink())
            .apply(new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.not_found))
            .into(ivwImage);
    }

    @Override
    public void onClick(View view){
        RecyclableAdapter adapter = getListener().getRecyclableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                getListener().onMakeToast("Item[" + position + "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.removeModelable(model)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        if(fromUser){
            model.setRating(rating);
            RecyclableAdapterListener listener = getListener();
            listener.getRecyclableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + position + "] set to " + rating );
        }
    }
}
