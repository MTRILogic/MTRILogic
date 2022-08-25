package com.mtrilogic.interfaces;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnMakeToastListener{
    void onNewTagName(String oldTag, String newTag);
}
