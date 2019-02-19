package com.mtrilogic.abstracts;

import android.support.v4.app.Fragment;

public abstract class Fragmentable extends Fragment{
    @SuppressWarnings("unused")
    protected static final String PAGE_TITLE = "TITLE", ITEM_ID = "ID";
    public abstract String getPageTitle();
    public abstract long getItemId();

    //pending class for review
}
