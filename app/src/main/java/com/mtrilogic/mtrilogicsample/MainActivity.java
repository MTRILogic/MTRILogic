package com.mtrilogic.mtrilogicsample;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Statable;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.mtrilogicsample.fragments.SampleExpandableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleInflatableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleRecyclableFragment;
import com.mtrilogic.mtrilogicsample.pages.SampleExpandablePage;
import com.mtrilogic.mtrilogicsample.pages.SampleInflatablePage;
import com.mtrilogic.mtrilogicsample.pages.SampleRecyclablePage;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        FragmentableListener, FragmentableAdapterListener, OnMakeToastListener{
    private static final String[] TITLES = {"INFLATABLES", "RECYCLABLES", "EXPANDABLES"};
    private static final String TAG = "MainActivityTAGY", LIST = "list", IDX = "idx";
    private ActionBar actionBar;
    private Statable paginableState;
    private FragmentableAdapter adapter;
    private FloatingActionsMenu fam;
    private ViewPager pager;
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

        paginableState = ViewModelProviders.of(this).get(Statable.class);

        if(savedInstanceState != null) {
            idx = paginableState.getListable().getIdx();
        }else {
            paginableState.init();
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
                if(paginableState.getListable().getModelableList().add(paginable)){
                    idx++;
                }
            }
            paginableState.update();

            final Observer<Listable<Paginable>> paginableObserver = new Observer<Listable<Paginable>>() {
                @Override
                public void onChanged(Listable<Paginable> listable) {
                    makeToast("Count: " + listable.getModelableCount());
                }
            };

            paginableState.getListableLiveData().observe(this, paginableObserver);
        }

        adapter = new FragmentableAdapter(getSupportFragmentManager(),this, paginableState.getListable().getModelableList());
        pager = findViewById(R.id.pager);
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

        fam = findViewById(R.id.fam);

        FloatingActionButton fabInflatable = findViewById(R.id.fab_inflatable);
        fabInflatable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 0;
                Paginable paginable = new SampleInflatablePage(TITLES[index], TITLES[index].toLowerCase(), idx, index);
                addPage(paginable);
            }
        });

        FloatingActionButton fabRecyclable = findViewById(R.id.fab_recyclable);
        fabRecyclable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 1;
                Paginable paginable = new SampleRecyclablePage(TITLES[index], TITLES[index].toLowerCase(), idx, index);
                addPage(paginable);
            }
        });

        FloatingActionButton fabExpandable = findViewById(R.id.fab_expandable);
        fabExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 2;
                Paginable paginable = new SampleExpandablePage(TITLES[index], TITLES[index].toLowerCase(), idx, index);
                addPage(paginable);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
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

    private void addPage(Paginable paginable){
        Listable<Paginable> listable = paginableState.getListable();
        if(listable.getModelableList().add(paginable)){
            listable.setIdx(++idx);
            adapter.notifyDataSetChanged();
            paginableState.update();
            pager.setCurrentItem(adapter.getCount() - 1);
        }
        fam.collapse();
    }

    private void makeToast(String line){
        Toast.makeText(this,line,Toast.LENGTH_LONG).show();
    }
}
