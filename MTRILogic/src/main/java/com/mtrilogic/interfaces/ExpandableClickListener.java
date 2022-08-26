package com.mtrilogic.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Modelable;

@SuppressWarnings("unused")
public interface ExpandableClickListener {
    boolean onChildLongClick(@NonNull View itemView, @NonNull Modelable modelable, int groupPosition, int childPosition, boolean lastChild);

    boolean onGroupLongClick(@NonNull View itemView, @NonNull Modelable modelable, int groupPosition, boolean expanded);

    void onChildClick(@NonNull View itemView, @NonNull Modelable modelable, int groupPosition, int childPosition, boolean lastChild);

    void onGroupClick(@NonNull View itemView, @NonNull Modelable modelable, int groupPosition, boolean expanded);
}
