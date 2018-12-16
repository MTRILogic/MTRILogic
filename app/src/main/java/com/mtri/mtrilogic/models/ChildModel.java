package com.mtri.mtrilogic.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.mtri.abstracts.Modelable;
import com.mtri.mtrilogic.R;

public class ChildModel extends Modelable{
    public static final Parcelable.Creator<ChildModel> CREATOR = new Parcelable.Creator<ChildModel>(){
        @Override
        public ChildModel createFromParcel(Parcel source){
            return new ChildModel(source);
        }

        @Override
        public ChildModel[] newArray(int size){
            return new ChildModel[size];
        }
    };

    private String title, content;
    private boolean checked;
    private int icon;

    public ChildModel(){
        super(R.id.CHILD);
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

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public int getIcon(){
        return icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    private ChildModel(Parcel src){
        super(src);
        checked = src.readInt() != 0;
        title = src.readString();
        content = src.readString();
        icon = src.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeInt(checked ? 1 : 0);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(icon);
    }
}
