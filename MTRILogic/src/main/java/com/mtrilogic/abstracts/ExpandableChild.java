package com.mtrilogic.abstracts;

import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;

import com.mtrilogic.interfaces.ExpandableItemListener;

@SuppressWarnings("unused")
public abstract class ExpandableChild<M extends Model> extends Expandable<M> {
    protected int childPosition;
    protected boolean lastChild;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public ExpandableChild(@NonNull Class<M> clazz, @NonNull View itemView, @NonNull ExpandableItemListener listener){
        super(clazz, itemView, listener);
    }

    /*==============================================================================================
    PUBLIC METHOD
    ==============================================================================================*/

    @NonNull
    @Override
    public View getItemView() {
        itemView.setOnClickListener(v -> listener.onExpandableChildClick(itemView, model, groupPosition, childPosition, lastChild));
        itemView.setOnLongClickListener(v -> listener.onExpandableChildLongClick(itemView, model, groupPosition, childPosition, lastChild));
        return super.getItemView();
    }

    public void bindModel(@NonNull Model model, int groupPosition, int childPosition, boolean lastChild){
        this.childPosition = childPosition;
        this.lastChild = lastChild;
        super.bindModel(model, groupPosition);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected final Model getGroup(){
        return listener.getModelMappable().getGroup(groupPosition);
    }

    protected final boolean autoDelete(@NonNull Model group){
        return listener.getModelMappable().deleteChild(group, model);
    }

    protected final void autoCollapse(@NonNull Model group){
        if (listener.getModelMappable().getChildCount(group) == 0){
            ExpandableListView lvwItems = listener.getExpandableListView();
            if (lvwItems.isGroupExpanded(groupPosition)){
                lvwItems.collapseGroup(groupPosition);
            }
        }
    }
}
