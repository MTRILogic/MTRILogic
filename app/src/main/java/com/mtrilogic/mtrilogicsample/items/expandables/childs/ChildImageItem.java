package com.mtrilogic.mtrilogicsample.items.expandables.childs;

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
import com.mtrilogic.abstracts.ExpandableChild;
import com.mtrilogic.abstracts.Modelable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.mtrilogicsample.R;
import com.mtrilogic.mtrilogicsample.models.ImageModel;
import com.mtrilogic.views.SquareImageView;

@SuppressWarnings({"unused","FieldCanBeLocal"})
public class ChildImageItem extends ExpandableChild implements View.OnClickListener, RatingBar.OnRatingBarChangeListener{
    private static final String TAG = "ChildDataItem";
    private TextView lblTitle, lblContent;
    private CheckBox chkItem;
    private SquareImageView ivwImage;
    private RatingBar ratingBar;
    private ImageModel model;
    private int groupPosition, childPosition;
    private boolean lastChild;

// ++++++++++++++++| PUBLIC CONSTRUCTORS |+++++++++++++++++++++++++++++++++++++

    public ChildImageItem(Context context, int resource){
        this(context,(ExpandableAdapterListener)context, resource);
    }

    public ChildImageItem(Context context, ExpandableAdapterListener listener, int resource){
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
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
        view.setTag(this);
        return view;
    }

    @Override
    public void onBindHolder(Modelable modelable, int groupPosition, int childPosition, boolean lastChild){
        model = (ImageModel)modelable;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        chkItem.setChecked(model.isChecked());
        Context context = getContext();
        lblTitle.setText(context.getString(R.string.title_item, model.getItemId()));
        lblContent.setText(context.getString(R.string.content_item, childPosition));
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
        ExpandableAdapter adapter = getListener().getExpandableAdapter();
        int id = view.getId();
        switch(id){
            case R.id.chk_item:
                boolean checked = chkItem.isChecked();
                model.setChecked(checked);
                adapter.notifyDataSetChanged();
                getListener().onMakeToast("Item[" + groupPosition + "," + childPosition + "] set to " + checked);
                break;
            case R.id.btn_delete:
                if(adapter.deleteChildModelable(adapter.getGroup(groupPosition), model)){
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
        if(fromUser){
            model.setRating(rating);
            ExpandableAdapterListener listener = getListener();
            listener.getExpandableAdapter().notifyDataSetChanged();
            listener.onMakeToast("Rating Bar[" + groupPosition + "][" + childPosition + "] set to " + rating );
        }
    }
}