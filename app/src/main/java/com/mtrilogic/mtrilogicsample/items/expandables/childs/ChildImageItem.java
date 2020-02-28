package com.mtrilogic.mtrilogicsample.items.expandables.childs;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.databinding.ItemChildImageBinding;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class ChildImageItem extends ExpandableChild<ImageModel, ItemChildImageBinding> implements
        RatingBar.OnRatingBarChangeListener{

    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;

    // ================< PUBLIC CONSTRUCTORS >======================================================

    public ChildImageItem(@NonNull ItemChildImageBinding binding, @NonNull ExpandableAdapterListener listener){
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
        ratingBar = itemView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, int childPosition, boolean lastChild){
        bindModel((ImageModel) modelable, groupPosition, childPosition, lastChild);
        chkItem.setChecked(model.isChecked());
        Context context = itemView.getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, childPosition));
        ratingBar.setRating(model.getRating());
        Glide.with(context)
                .load(model.getImageLink())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.not_found))
                .into(ivwImage);
    }

    @Override
    public void onChanged(ImageModel imageModel) {

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if(adapter != null && fromUser){
            model.setRating(rating);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + groupPosition + "][" + childPosition +
                    "] set to " + rating );
        }
    }

    // ================< PRIVATE METHODS >==========================================================

    private void updateChecked(){
        ExpandableAdapter adapter = listener.getExpandableAdapter();
        if (adapter != null) {
            boolean checked = chkItem.isChecked();
            model.setChecked(checked);
            adapter.notifyDataSetChanged();
            listener.onMakeToast("Item [" + groupPosition + "," + childPosition +
                    "] set to " + checked);
        }
    }

    private void clickImage(){
        listener.onMakeToast("Image [" + groupPosition + "][" + childPosition + "]");
    }
}