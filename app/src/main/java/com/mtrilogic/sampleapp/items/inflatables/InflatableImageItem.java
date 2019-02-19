package com.mtrilogic.sampleapp.items.inflatables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

public class InflatableImageItem extends Inflatable implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;

    @SuppressWarnings("unused")
    public InflatableImageItem(Context context){
        this(context,(InflatableAdapterListener)context);
    }

    @SuppressWarnings("WeakerAccess")
    public InflatableImageItem(Context context, InflatableAdapterListener listener){
        super(context,listener);
    }

    @Override
    public View getInflatableView(ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(getLayoutResource(),parent,false);
        ivwImage = view.findViewById(R.id.ivw_image);
        ivwImage.setOnClickListener(this);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable){
        model = (ImageModel)modelable;
        ratingBar.setRating(model.getRating());
        Glide.with(context)
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
                listener.onMakeToast("Image [" + model.getPosition() + "] clicked");
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        if(fromUser){
            model.setRating(rating);
            listener.getInflatableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + model.getPosition() + "] set to " + rating );
        }
    }
}
