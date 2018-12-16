package com.mtri.abstracts;

import android.support.v4.app.Fragment;

public abstract class Fragmentable extends Fragment{
    private String title;
    private long id;

    public void setPageTitle(String title){
        this.title = title;
    }

    public String getPageTitle(){
        return title;
    }

    public void setItemId(long id){
        this.id = id;
    }

    public long getItemId(){
        return id;
    }
}
