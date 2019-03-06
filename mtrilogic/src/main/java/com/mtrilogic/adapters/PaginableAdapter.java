package com.mtrilogic.adapters;

import android.os.Parcel;

import com.mtrilogic.abstracts.Paginable;

public class PaginableAdapter extends Paginable{
    public static final Creator<PaginableAdapter> CREATOR = new Creator<PaginableAdapter>(){
        @Override
        public PaginableAdapter createFromParcel(Parcel source){
            return new PaginableAdapter(source);
        }

        @Override
        public PaginableAdapter[] newArray(int size){
            return new PaginableAdapter[size];
        }
    };

    public PaginableAdapter(String pageTitle, long itemId, int viewType){
        setPageTitle(pageTitle);
        setItemId(itemId);
        setViewType(viewType);
    }

    //for use with FragmentableStateAdapter
    @SuppressWarnings("unused")
    public PaginableAdapter(String pageTitle, int viewType){
        setPageTitle(pageTitle);
        setViewType(viewType);
    }

    private PaginableAdapter(Parcel src){
        super(src);
    }

    @Override
    protected void onReadFromParcel(Parcel src){}

    @Override
    protected void onWriteToParcel(Parcel dst, int flags){}
}
