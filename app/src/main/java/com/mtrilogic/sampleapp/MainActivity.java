package com.mtrilogic.sampleapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.sampleapp.fragments.ExpandableFragment;
import com.mtrilogic.sampleapp.fragments.InflatableFragment;
import com.mtrilogic.sampleapp.fragments.RecyclableFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnMakeToastListener{
    private List<Fragmentable> fragmentables;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        fragmentables = new ArrayList<>();
        FragmentableAdapter adapter = new FragmentableAdapter(manager, fragmentables);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);

        if(savedInstanceState == null){
            fragmentables.add(InflatableFragment.getInstance("Inflatable",0));
            fragmentables.add(RecyclableFragment.getInstance("Recyclable",1));
            fragmentables.add(ExpandableFragment.getInstance("Expandable",2));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1){

    }

    @Override
    public void onPageSelected(int position){
        setTitle(fragmentables.get(position).getPageTitle());
    }

    @Override
    public void onPageScrollStateChanged(int i){

    }

    @Override
    public void onMakeToast(String line){
        makeToast(line);
    }

    private void makeToast(String line){
        Toast.makeText(this,line,Toast.LENGTH_LONG).show();
    }
}
