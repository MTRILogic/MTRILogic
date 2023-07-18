package com.mtrilogic.mtrilogic.models;

import android.os.Bundle;

import com.mtrilogic.abstracts.ListableModel;
import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.ModelCreator;

@SuppressWarnings("unused")
public class DefaultListableModel<M extends Model> extends ListableModel<M> {

    public static final Creator<DefaultListableModel<? extends Model>> CREATOR = new ModelCreator<DefaultListableModel<? extends Model>>() {
        @Override
        public DefaultListableModel<? extends Model> createFromData(Bundle data) {
            return new DefaultListableModel<>(data);
        }

        @Override
        public DefaultListableModel<? extends Model>[] newArray(int size) {
            return new DefaultListableModel<?>[size];
        }
    };

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public DefaultListableModel() {
        super();
    }

    public DefaultListableModel(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTOR
    ==============================================================================================*/

    protected DefaultListableModel(Bundle data) {
        super(data);
    }
}
