package com.mtrilogic.mtrilogicsample.items.inflatables;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.BindingInflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemImageBinding;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused"})
public class InflatableImageItem extends BindingInflatable<ImageModel, ItemImageBinding> implements
        RatingBar.OnRatingBarChangeListener{

    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public InflatableImageItem(@NonNull ItemImageBinding binding, @NonNull InflatableAdapterListener listener){
        super(binding, listener);
        chkItem = binding.chkItem;
        chkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChecked();
            }
        });
        lblTitle = binding.lblTitle;
        lblContent = binding.lblContent;
        ImageButton btnDelete = binding.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoDelete();
            }
        });
        ivwImage = binding.ivwImage;
        ivwImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage();
            }
        });
        ratingBar = binding.ratingBar;
        ratingBar.setOnRatingBarChangeListener(this);
    }

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected ImageModel getModelFromModelable(@NonNull Modelable modelable) {
        return (ImageModel) modelable;
    }

    @Override
    protected void onBindHolder(){
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, position));
        ratingBar.setRating(model.getRating());
        Glide.with(context)
                .load(model.getImageLink())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.not_found))
                .into(ivwImage);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================
    
    @Override
    public void onChanged(ImageModel imageModel) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if(adapter != null && fromUser){
            model.setRating(rating);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Rating Bar [" + position + "] set to " + rating );
        }
    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        InflatableAdapter adapter = listener.getInflatableAdapter();
        if (adapter != null){
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Item [" + position + "] set to " + checked);
        }
    }

    private void clickImage(){
        listener.onMakeToast("Image [" + position + "]");
    }
}
