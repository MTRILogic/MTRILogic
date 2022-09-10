package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnMakeToastListener{
    void onNewTagName(@NonNull String oldTag, @NonNull String newTag);
}
