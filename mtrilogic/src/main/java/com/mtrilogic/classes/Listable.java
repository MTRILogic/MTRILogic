package com.mtrilogic.classes;

import java.util.ArrayList;

public class Listable<M>{
    public ArrayList<M> list;
    public long idx;

    public Listable(){}

    public Listable(ArrayList<M> list){
        this.list = list;
    }
}
