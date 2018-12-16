package com.mtri.mtrilogic.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtri.abstracts.Modelable;
import com.mtri.mtrilogic.R;

public class GroupModel extends Modelable{
    public static final Parcelable.Creator<GroupModel> CREATOR = new Parcelable.Creator<GroupModel>(){
        @Override
        public GroupModel createFromParcel(Parcel source){
            return new GroupModel(source);
        }

        @Override
        public GroupModel[] newArray(int size){
            return new GroupModel[size];
        }
    };

    private String title;
    private boolean checked;

    public GroupModel(){
        super(R.id.GROUP);
    }

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    private GroupModel(Parcel src){
        super(src);
        checked = src.readInt() != 0;
        title = src.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeInt(checked ? 1 : 0);
        dest.writeString(title);
    }
}
