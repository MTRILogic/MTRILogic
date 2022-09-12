package com.mtrilogic.mtrilogic.models;

import android.os.Bundle;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.ModelCreator;

@SuppressWarnings("unused")
public class DefaultModel extends Model {
    public static final Creator<DefaultModel> CREATOR = new ModelCreator<DefaultModel>() {
        @Override
        public DefaultModel createFromData(Bundle data) {
            return new DefaultModel(data);
        }

        @Override
        public DefaultModel[] newArray(int size) {
            return new DefaultModel[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultModel() {
        super();
    }

    public DefaultModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultModel(Bundle data) {
        super(data);
    }
}
