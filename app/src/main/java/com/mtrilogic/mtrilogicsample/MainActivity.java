package com.mtrilogic.mtrilogicsample;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.mtrilogicsample.fragments.SampleExpandableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleInflatableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleRecyclableFragment;
import com.mtrilogic.mtrilogicsample.pages.SampleExpandablePage;
import com.mtrilogic.mtrilogicsample.pages.SampleInflatablePage;
import com.mtrilogic.mtrilogicsample.pages.SampleRecyclablePage;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        FragmentableListener, FragmentableAdapterListener, OnMakeToastListener{
    private static final String[] TITLES = {"INFLATABLES", "RECYCLABLES", "EXPANDABLES"};
    private static final String TAG = "MainActivityTAGY", LIST = "list", IDX = "idx";
    private ActionBar actionBar;
    private ArrayList<Paginable> paginableList;
    private FragmentableAdapter adapter;
    private long idx;

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

        if(savedInstanceState != null){
            paginableList = savedInstanceState.getParcelableArrayList(LIST);
            idx = savedInstanceState.getLong(IDX);
        }else {
            paginableList = new ArrayList<>();
            for(int i = 0; i < 3; i++){
                String title = TITLES[i];
                Paginable paginable = null;
                switch(i){
                    case 0:
                        paginable = new SampleInflatablePage(title, title.toLowerCase(), idx, i);
                        break;
                    case 1:
                        paginable = new SampleRecyclablePage(title, title.toLowerCase(), idx, i);
                        break;
                    case 2:
                        paginable = new SampleExpandablePage(title, title.toLowerCase(), idx, i);
                        break;
                }
                if(paginableList.add(paginable)){
                    idx++;
                }
            }
        }

        adapter = new FragmentableAdapter(getSupportFragmentManager(),this, paginableList);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        if(adapter.getCount() > 0){
            adapter.notifyDataSetChanged();
        }
        if(pager.getCurrentItem() == 0){
            pageSelected(0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList(LIST, paginableList);
        outState.putLong(IDX, idx);
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
    public Fragmentable getFragmentable(Paginable paginable, int position){
        switch(paginable.getViewType()){
            case 0:
                return Fragmentable.getInstance(paginable, new SampleInflatableFragment());
            case 1:
                return Fragmentable.getInstance(paginable, new SampleRecyclableFragment());
            case 2:
                return Fragmentable.getInstance(paginable, new SampleExpandableFragment());
        }
        return null;
    }

    @Override
    public void onPositionChanged(int position){
        //unused
    }

    @Override
    public FragmentableAdapter getFragmentableAdapter(){
        return adapter;
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
