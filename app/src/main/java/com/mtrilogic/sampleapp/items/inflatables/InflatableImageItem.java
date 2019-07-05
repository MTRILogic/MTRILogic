package com.mtrilogic.sampleapp.items.inflatables;

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
import com.mtrilogic.abstracts.Inflatable;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.InflatableAdapter;
import com.mtrilogic.interfaces.InflatableAdapterListener;
import com.mtrilogic.sampleapp.R;
import com.mtrilogic.sampleapp.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused"})
public class InflatableImageItem extends Inflatable implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;
    private int position;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public InflatableImageItem(Context context, int resource){
        this(context, (InflatableAdapterListener)context, resource);
    }

    public InflatableImageItem(Context context, InflatableAdapterListener listener, int resource){
        super(context, listener, resource);
    }

// ++++++++++++++++| PUBLIC OVERRIDE METHODS |+++++++++++++++++++++++++++++++++

    @Override
    public View getInflatableView(ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResource(),parent,false);
        chkItem = view.findViewById(R.id.chk_item);
        chkItem.setOnClickListener(this);
        lblTitle = view.findViewById(R.id.lbl_title);
        lblContent = view.findViewById(R.id.lbl_content);
        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);
        ivwImage = view.findViewById(R.id.ivw_image);
        ivwImage.setOnClickListener(this);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
        view.setTag(this);
        return view;
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
        InflatableAdapter adapter = getListener().getInflatableAdapter();
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
            InflatableAdapterListener listener = getListener();
            listener.getInflatableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + position + "] set to " + rating );
        }
    }
}
