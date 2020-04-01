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
import com.mtrilogic.interfaces.FragmentListener;
import com.mtrilogic.interfaces.FragmentableListener;
import com.mtrilogic.mtrilogicsample.databinding.ActivityMainBinding;
import com.mtrilogic.mtrilogicsample.fragments.SampleExpandableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleInflatableFragment;
import com.mtrilogic.mtrilogicsample.fragments.SampleRecyclableFragment;
import com.mtrilogic.mtrilogicsample.pages.SampleMapPage;
import com.mtrilogic.mtrilogicsample.pages.SampleListPage;
import com.mtrilogic.mtrilogicsample.states.PageState;
import com.mtrilogic.mtrilogicsample.types.FragmentableType;

@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        FragmentableListener, FragmentListener {

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
    private static final int[] VIEW_TYPES = {
            FragmentableType.INFLATABLE,
            FragmentableType.RECYCLABLE,
            FragmentableType.EXPANDABLE
    };

    private static final String TAG = "MainActivityTAG", LIST = "list", IDX = "idx";

    private ActionBar actionBar;
    private PageState pageState;
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
                makeToast("Count: " + listable.getCount());
            }
        };

        pageState = new ViewModelProvider(this).get(PageState.class);
        pageState.getListableLiveData().observe(this, paginableObserver);

        if(savedInstanceState != null) {
            idx = pageState.getListable().getIdx();
        }else {
            idx = 0;
            for(int i = 0; i < 3; i++){
                String pageTitle = getString(PAGE_TITLES[i]);
                Paginable paginable = newPaginable(VIEW_TYPES[i], pageTitle, TAG_NAMES[i] + idx);
                if(paginable != null && pageState.getListable().getList().add(paginable)){
                    idx++;
                }
            }
            pageState.getListable().setIdx(idx);
            pageState.setUpdate();
        }

        Listable<Paginable> listable = pageState.getListable();
        adapter = new FragmentableAdapter(getSupportFragmentManager(), listable, this);
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
                addPaginable(newPaginable(FragmentableType.INFLATABLE, "Inflatable", "inflatable" + idx));
            }
        });

        FloatingActionButton fabRecyclable = binding.fabRecyclable;
        fabRecyclable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPaginable(newPaginable(FragmentableType.RECYCLABLE, "Recyclable", "recyclable" + idx));
            }
        });

        FloatingActionButton fabExpandable = binding.fabExpandable;
        fabExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPaginable(newPaginable(FragmentableType.EXPANDABLE, "Expandable", "expandable" + idx));
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
    public Fragmentable getFragmentable(@NonNull Paginable paginable){
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
    public void onChangePosition(int position) {
        if (position == Base.INVALID_POSITION){
            actionBar.setTitle(R.string.app_name);
        }else {
            pageSelected(position);
        }
        pageState.setUpdate();
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

    // ================< PRIVATE METHODS >==========================================================

    private void pageSelected(int position){
        actionBar.setTitle(adapter.getPageTitle(position));
    }

    private void addPaginable(Paginable paginable){
        if (paginable != null) {
            Listable<Paginable> listable = pageState.getListable();
            if (listable.append(paginable)) {
                adapter.notifyDataSetChanged();
                pageState.setUpdate();
                int index = adapter.getCount() - 1;
                if (index != 0) {
                    pager.setCurrentItem(index);
                } else {
                    pageSelected(0);
                }
            }
            fam.collapse();
        }
    }

    private Paginable newPaginable(int viewType, String pageTitle, String tagName){
        switch (viewType){
            case FragmentableType.INFLATABLE:
                return new SampleListPage(pageTitle, tagName, FragmentableType.INFLATABLE);
            case FragmentableType.RECYCLABLE:
                return new SampleListPage(pageTitle, tagName, FragmentableType.RECYCLABLE);
            case FragmentableType.EXPANDABLE:
                return new SampleMapPage(pageTitle, tagName);
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
