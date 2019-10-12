package com.mtrilogic.fragments;

import android.view.View;
import android.widget.ExpandableListView;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.adapters.ExpandableAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.classes.Mapable;
import com.mtrilogic.interfaces.ExpandableAdapterListener;
import com.mtrilogic.interfaces.ExpandableListener;
import com.mtrilogic.interfaces.FragmentableAdapterListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.pages.ExpandablePage;

@SuppressWarnings("unused")
public abstract class ExpandableFragment<P extends ExpandablePage> extends Fragmentable<P> implements
        ExpandableListener, ExpandableAdapterListener {
    private static final String TAG = "ExpandableFragment";
    private ExpandableAdapter adapter;

    protected void init(View view, int groupTypeCount, int childTypeCount){
        ExpandablePage page = getPage();
        if (page != null) {
            Listable groupListable = page.getGroupListable();
            Mapable childMapable = page.getChildMapable();
            adapter = new ExpandableAdapter(this, groupListable, childMapable, groupTypeCount, childTypeCount);
            ExpandableListView lvwItems = view.findViewById(R.id.lvw_items);
            lvwItems.setAdapter(adapter);
        }
    }

    @Override
    public ExpandableAdapter getExpandableAdapter(){
        return adapter;
    }

    @Override
    public void onMakeToast(String line){
        FragmentableAdapterListener listener = getListener();
        if (listener != null) {
            listener.onMakeToast(line);
        }
    }
}
