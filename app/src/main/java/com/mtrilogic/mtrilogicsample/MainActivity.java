package com.mtrilogic.mtrilogicsample;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.adapters.FragmentableAdapter;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.StateViewModel;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.interfaces.OnMakeToastListener;
import com.mtrilogic.mtrilogicsample.databinding.ActivityMainBinding;
import com.mtrilogic.mtrilogicsample.fragments.SampleExpandableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleInflatableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleRecyclableFragment;
import com.mtrilogic.mtrilogicsample.pages.SampleMapPaginable;
import com.mtrilogic.mtrilogicsample.pages.SampleListPaginable;
import com.mtrilogic.mtrilogicsample.types.FragmentableType;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        FragmentableListener, FragmentableAdapterListener, OnMakeToastListener{

    private static final int[] PAGE_TITLES = {
            R.string.inflatable,
            R.string.recyclable,
            R.string.expandable
    };
    private static final String[] TAG_NAMES = {
            "inflatable",
            "recyclable",
            "expandable"
    };
    private static final int[] TYPES = {
            FragmentableType.INFLATABLE,
            FragmentableType.RECYCLABLE,
            FragmentableType.EXPANDABLE
    };
    private static final String TAG = "MainActivityTAG", LIST = "list", IDX = "idx";
    private ActionBar actionBar;
    private StateViewModel paginableState;
    private FragmentableAdapter adapter;
    private FloatingActionsMenu fam;
    private ViewPager pager;
    private long idx;

    // ================< PROTECTED OVERRIDE METHODS >===============================================

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Observer<Listable<Paginable>> paginableObserver = new Observer<Listable<Paginable>>() {
            @Override
            public void onChanged(Listable<Paginable> listable) {
                makeToast("Count: " + listable.getModelableCount());
            }
        };

        paginableState = new ViewModelProvider(this).get(StateViewModel.class);
        paginableState.getListableLiveData().observe(this, paginableObserver);

        if(savedInstanceState != null) {
            idx = paginableState.getListable().getIdx();
        }else {
            for(int i = 0; i < 3; i++){
                Paginable paginable = getNewPaginable(i);
                if(paginable != null && paginableState.getListable().getModelableList().add(paginable)){
                    idx++;
                }
            }
            paginableState.getListable().setIdx(idx);
            paginableState.setUpdate();
        }

        adapter = new FragmentableAdapter(getSupportFragmentManager(),this,
                paginableState.getListable().getModelableList());
        pager = binding.pager;
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(pager);

        if(tabs.getTabCount() > 0){
            adapter.notifyDataSetChanged();
        }
        if(tabs.getSelectedTabPosition() == 0){
            pageSelected(0);
        }

        fam = binding.fam;

        FloatingActionButton fabInflatable = binding.fabInflatable;
        fabInflatable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPaginable(getNewPaginable(0));
            }
        });

        FloatingActionButton fabRecyclable = binding.fabRecyclable;
        fabRecyclable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPaginable(getNewPaginable(1));
            }
        });

        FloatingActionButton fabExpandable = binding.fabExpandable;
        fabExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPaginable(getNewPaginable(2));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
    }

    // ================< PUBLIC OVERRIDE METHODS >==================================================

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
            case FragmentableType.INFLATABLE:
                return Fragmentable.getInstance(paginable, new SampleInflatableFragment());
            case FragmentableType.RECYCLABLE:
                return Fragmentable.getInstance(paginable, new SampleRecyclableFragment());
            case FragmentableType.EXPANDABLE:
                return Fragmentable.getInstance(paginable, new SampleExpandableFragment());
        }
        return null;
    }

    @Override
    public void onPositionChanged(int position){
        if (position == Base.INVALID_POSITION){
            if (adapter.getCount() == 0) {
                actionBar.setTitle(R.string.app_name);
            }
        }else {
            pageSelected(position);
        }
        paginableState.setUpdate();
    }

    @Override
    public FragmentableAdapter getFragmentableAdapter(){
        return adapter;
    }

    @Override
    public ViewPager getViewPager() {
        return pager;
    }

    @Override
    public void onMakeToast(String line){
        makeToast(line);
    }

    @Override
    public void onMakeLog(String line) {
        makeLog(line);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        makeLog("Exception: Thread: " + t + ", Line number: " + e.getStackTrace()[0].getLineNumber());
    }

    // ================< PRIVATE METHODS >==========================================================

    private void pageSelected(int position){
        actionBar.setTitle(adapter.getPageTitle(position));
    }

    private void addNewPaginable(Paginable paginable){
        if (paginable != null){
            Listable<Paginable> listable = paginableState.getListable();
            if (listable.appendModelable(paginable)){
                listable.setIdx(++idx);
                adapter.notifyDataSetChanged();
                paginableState.setUpdate();
                int index = adapter.getCount() - 1;
                if (index == 0){
                    pageSelected(0);
                }else {
                    pager.setCurrentItem(index);
                }
            }
        }
        fam.collapse();
    }

    private Paginable getNewPaginable(int index){
        String pageTitle  = getString(PAGE_TITLES[index]);
        String tagName = TAG_NAMES[index];
        int viewType = TYPES[index];
        switch (viewType){
            case FragmentableType.INFLATABLE:
                return new SampleListPaginable(pageTitle, tagName, idx, FragmentableType.INFLATABLE);
            case FragmentableType.RECYCLABLE:
                return new SampleListPaginable(pageTitle, tagName, idx, FragmentableType.RECYCLABLE);
            case FragmentableType.EXPANDABLE:
                return new SampleMapPaginable(pageTitle, tagName, idx);
        }
        return null;
    }

    private void makeToast(String line){
        Toast.makeText(this,line,Toast.LENGTH_LONG).show();
    }

    private void makeLog(String line){
        Log.d(TAG, "MTRI: " + line);
    }
}
