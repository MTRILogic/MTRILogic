package com.mtrilogic.mtrilogic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mtrilogic.mtrilogic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityMainBinding.inflate(getLayoutInflater()).getRoot());
    }
}