package com.mtrilogic.adapters;

import android.os.Parcel;

import com.mtrilogic.abstracts.Paginable;

@SuppressWarnings({"unused","WeakerAccess"})
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

    private String pageTitle;
    private int viewType;

    // +++++++++++++++++| PUBLIC CONSTRUCTORS |++++++++++++++++++++++++++++++++

    public PaginableAdapter(){}

    public PaginableAdapter(String pageTitle){
        this(pageTitle,0);
    }

    public PaginableAdapter(String pageTitle, int viewType){
        this.pageTitle = pageTitle;
        this.viewType = viewType;
    }

    // +++++++++++++++++| PRIVATE CONSTRUCTORS |+++++++++++++++++++++++++++++++

    private PaginableAdapter(Parcel src){
        super(src);
    }

    // +++++++++++++++++| PUBLIC METHODS |+++++++++++++++++++++++++++++++++++++

    public void setPageTitle(String pageTitle){
        this.pageTitle = pageTitle;
    }

    public void setViewType(int viewType){
        this.viewType = viewType;
    }

    // +++++++++++++++++| OVERRIDE PUBLIC PROTECTED METHODS |++++++++++++++++++

    @Override
    public String getPageTitle(){
        return pageTitle;
    }

    @Override
    public int getViewType(){
        return viewType;
    }


    // +++++++++++++++++| OVERRIDE PROTECTED METHODS |+++++++++++++++++++++++++

    @Override
    protected void onReadFromParcel(Parcel in){
        pageTitle = in.readString();
        viewType = in.readInt();
    }

    @Override
    protected void onWriteToParcel(Parcel out){
        out.writeString(pageTitle);
        out.writeInt(viewType);
    }
}
