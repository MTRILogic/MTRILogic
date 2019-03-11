package com.mtrilogic.sampleapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.adapters.PaginableAdapter;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.sampleapp.fragments.ExpandableFragment;
import com.mtrilogic.sampleapp.fragments.InflatableFragment;
import com.mtrilogic.sampleapp.fragments.RecyclableFragment;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, FragmentableListener, OnMakeToastListener{
    private static final String TAG = "MainActivityTAGY";
    private ActionBar actionBar;
    private FragmentableAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager manager = getSupportFragmentManager();
        adapter = new FragmentableAdapter(manager,this);
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        int count = 0;
        int position = 0;
        if(savedInstanceState == null){
            if(adapter.addFragmentable(InflatableFragment.getInstance(new PaginableAdapter("Inflatable",0)))){
                count++;
            }
            if(adapter.addFragmentable(RecyclableFragment.getInstance(new PaginableAdapter("Recyclable",1)))){
                count++;
            }
            if(adapter.addFragmentable(ExpandableFragment.getInstance(new PaginableAdapter("Expandable",2)))){
                count++;
            }
        }else {
            adapter.restorePaginableInstance(savedInstanceState);
            position = savedInstanceState.getInt("position");
            position = position < adapter.getCount() ? position : 0;
            count= adapter.getCount();
        }
        if(count > 0){
            if(position > 0){
                pager.setCurrentItem(position);
            }else {
                pageSelected(0);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt("position",pager.getCurrentItem());
        adapter.savePaginableInstance(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1){}

    @Override
    public void onPageSelected(int position){
        pageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int i){}

    @Override
    public Fragmentable getFragmentableInstance(Paginable page){
        switch(page.getViewType()){
            case 0:
                return InflatableFragment.getInstance(page);
            case 1:
                return RecyclableFragment.getInstance(page);
            case 2:
                return ExpandableFragment.getInstance(page);
        }
        return null;
    }

    @Override
    public void onMakeToast(String line){
        makeToast(line);
    }

    private void pageSelected(int position){
        actionBar.setTitle(adapter.getPageTitle(position));
    }

    private void makeToast(String line){
        Toast.makeText(this,line,Toast.LENGTH_LONG).show();
    }
}
