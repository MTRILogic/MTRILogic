package com.mtrilogic.mtrilogicsample.items.inflatables;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused"})
public class InflatableImageItem extends Inflatable implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;
    private int position;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public InflatableImageItem(Context context, int resource, ViewGroup parent){
        this(context, resource, parent, (InflatableAdapterListener)context);
    }

    public InflatableImageItem(Context context, int resource, ViewGroup parent, InflatableAdapterListener listener){
        super(context, resource, parent, listener);
        chkItem = itemView.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = itemView.findViewById(R.id.lbl_title);
        lblContent = itemView.findViewById(R.id.lbl_content);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        ivwImage = itemView.findViewById(R.id.ivw_image);
        ivwImage.setOnClickListener(this);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
        InflatableAdapter adapter = listener.getInflatableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                listener.onMakeToast("Item[" + position + "] set to " + checked);
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
            listener.getInflatableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + position + "] set to " + rating );
        }
    }
}
