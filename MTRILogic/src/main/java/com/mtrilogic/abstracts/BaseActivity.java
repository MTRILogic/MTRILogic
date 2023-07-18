package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mtrilogic.interfaces.Activitable;
import com.mtrilogic.interfaces.FragmentableListener;

@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity implements Activitable, FragmentableListener {

    private static final String TAG_NAME = "tagName";

    protected String tagName;

    /*==============================================================================================
    PROTECTED OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            tagName = savedInstanceState.getString(TAG_NAME);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(TAG_NAME, tagName);
        super.onSaveInstanceState(outState);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public final void onNewTagName(@NonNull String oldTag, @NonNull String newTag) {
        if (oldTag.equals(tagName)){
            tagName = newTag;
        }
    }

    @Override
    public final void onMakeToast(String line) {
        makeToast(line);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected final void makeToast(String line){
        Toast.makeText(this, line, Toast.LENGTH_LONG).show();
    }
}
