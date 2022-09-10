package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

@SuppressWarnings({"unused", "EmptyMethod", "SameReturnValue"})
public interface ExpandableListener extends OnMakeToastListener{
    boolean onExpandableChildLongClick(@NonNull View itemView, @NonNull Model model, int groupPosition, int childPosition, boolean lastChild);

    boolean onExpandableGroupLongClick(@NonNull View itemView, @NonNull Model model, int groupPosition, boolean expanded);

    void onExpandableChildClick(@NonNull View itemView, @NonNull Model model, int groupPosition, int childPosition, boolean lastChild);

    void onExpandableGroupClick(@NonNull View itemView, @NonNull Model model, int groupPosition, boolean expanded);
}
