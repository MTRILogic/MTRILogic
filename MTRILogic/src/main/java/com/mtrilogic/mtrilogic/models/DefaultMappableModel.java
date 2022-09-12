package com.mtrilogic.mtrilogic.models;

import android.os.Bundle;

import com.mtrilogic.abstracts.MappableModel;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.ModelCreator;

@SuppressWarnings("unused")
public class DefaultMappableModel<M extends Model> extends MappableModel<M> {
    public static final Creator<DefaultMappableModel<? extends Model>> CREATOR = new ModelCreator<DefaultMappableModel<? extends Model>>() {
        @Override
        public DefaultMappableModel<? extends Model> createFromData(Bundle data) {
            return new DefaultMappableModel<>(data);
        }

        @Override
        public DefaultMappableModel<? extends Model>[] newArray(int size) {
            return new DefaultMappableModel<?>[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultMappableModel() {
        super();
    }

    public DefaultMappableModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultMappableModel(Bundle data) {
        super(data);
    }
}
