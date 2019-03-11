package com.mtrilogic.sampleapp.items.recyclables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings("unused")
public class RecyclableImageItem extends Recyclable implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public RecyclableImageItem(Context context){
        this(context,(RecyclableAdapterListener)context);
    }

    public RecyclableImageItem(Context context, RecyclableAdapterListener listener){
        super(new View(context),context,listener);
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private RecyclableImageItem(View view, Context context, RecyclableAdapterListener listener){
        super(view,context,listener);
        ivwImage = itemView.findViewById(R.id.ivw_image);
        ivwImage.setOnClickListener(this);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    // +++++++++++++++++| OVERRIDE PUBLIC METHODS |++++++++++++++++++++++++++++

    @Override
    public Recyclable getRecyclableHolder(ViewGroup parent){
        Context context = getContext();
        View view = LayoutInflater.from(context).inflate(getLayoutResource(),parent,false);
        return new RecyclableImageItem(view,context,getListener());
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (ImageModel)modelable;
        ratingBar.setRating(model.getRating());
        Glide.with(getContext())
            .load(model.getImageLink())
            .apply(new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.not_found))
            .into(ivwImage);
    }

    @Override
    public int getLayoutResource(){
        return R.layout.item_image;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.ivw_image:
                getListener().onMakeToast("Image [" + model.getPosition() + "] clicked");
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        if(fromUser){
            model.setRating(rating);
            RecyclableAdapterListener listener = getListener();
            listener.getRecyclableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + model.getPosition() + "] set to " + rating );
        }
    }
}
