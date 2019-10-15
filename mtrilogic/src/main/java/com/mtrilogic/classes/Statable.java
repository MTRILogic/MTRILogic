package com.mtrilogic.classes;

import androidx.lifecycle.ViewModel;

import com.mtrilogic.abstracts.Paginable;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Statable extends ViewModel {
    private ArrayList<Paginable> paginableList;
    private long idx;

    public ArrayList<Paginable> getPaginableList() {
        return paginableList;
    }

    public void setPaginableList(ArrayList<Paginable> paginableList) {
        this.paginableList = paginableList;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }
}
