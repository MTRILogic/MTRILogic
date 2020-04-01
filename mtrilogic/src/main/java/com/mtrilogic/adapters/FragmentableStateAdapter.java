package com.mtrilogic.adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;

import com.mtrilogic.abstracts.Fragmentable;
import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.classes.Base;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.FragmentableListener;

import java.util.ArrayList;

/**
 * Implementation of {@link PagerAdapter} that
 * uses a {@link Fragment} to manage each page. This class also handles
 * saving and restoring of fragment's state.
 *
 * <p>This version of the pager is more useful when there are a large number
 * of pages, working more like a list view.  When pages are not visible to
 * the user, their entire fragment may be destroyed, only keeping the saved
 * state of that fragment.  This allows the pager to hold on to much less
 * memory associated with each visited page as compared to
 * {@link FragmentPagerAdapter} at the cost of potentially more overhead when
 * switching between pages.
 *
 * <p>When using FragmentPagerAdapter the host ViewPager must have a
 * valid ID set.</p>
 *
 * <p>Subclasses only need to implement {@link #getItem(int)}
 * and {@link #getCount()} to have a working adapter.
 *
 * <p>Here is an example implementation of a pager containing fragments of
 * lists:
 */
@SuppressWarnings({"deprecation", "unused", "WeakerAccess"})
public class FragmentableStateAdapter extends PagerAdapter {
    private static final String TAG = "FragmentStatePagerAdapt", LIST = "list";
    private static final boolean DEBUG = false;

    /**
     * Indicates that only the current fragment will be in the {@link Lifecycle.State#RESUMED}
     * state. All other Fragments are capped at {@link Lifecycle.State#STARTED}.
     *
     * @see #FragmentableStateAdapter(FragmentManager,Listable,FragmentableListener)
     */
    private static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    private final FragmentManager manager;
    private final int behavior;

    private ArrayList<Fragment.SavedState> savedStateList = new ArrayList<>();
    private ArrayList<Fragmentable> fragmentableList = new ArrayList<>();
    private ArrayList<String> tagNameList = new ArrayList<>();
    private Listable<Paginable> listable;
    private FragmentableListener listener;

    private FragmentTransaction transaction = null;
    private Fragmentable fragmentable = null;

    // ==< PUBLIC CONSTRUCTORS >====================================================================

    /**
     *
     * @param manager fragment manager that will interact with this adapter
     */
    public FragmentableStateAdapter(@NonNull FragmentManager manager, @NonNull Listable<Paginable> listable,
                                    @NonNull FragmentableListener listener) {
        behavior = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
        this.listable = listable;
        this.listener = listener;
        this.manager = manager;
    }

    // ==< PUBLIC METHODS >=========================================================================

    public Listable<Paginable> getListable() {
        return listable;
    }

    public boolean setListable(@NonNull Listable<Paginable> listable) {
        if (this.listable != listable) {
            this.listable = listable;
            return true;
        }
        return false;
    }

    /**
     * Clear the saved state for the given fragment position.
     */
    public void removeSavedState(int position) {
        if(position < savedStateList.size()) {
            savedStateList.set(position, null);
        }
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @NonNull
    public Fragmentable getItem(int position){
        return listener.getFragmentable(getPaginable(position));
    }

    public String getTagName(int position) {
        return getPaginable(position).getTagName();
    }

    public long getItemId(int position){
        return getPaginable(position).getItemId();
    }

    // PUBLIC OVERRIDE METHODS =====================================================================

    @Override
    public int getCount() {
        return getPaginableList().size();
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // If we already have this item instantiated, there is nothing
        // to do.  This can happen when we are restoring the entire pager
        // from its saved state, where the fragment manager has already
        // taken care of restoring the fragments we previously had instantiated.
        Fragmentable fragmentable;
        if (fragmentableList.size() > position) {
            fragmentable = fragmentableList.get(position);
            if (fragmentable != null) {
                return fragmentable;
            }
        }

        if (transaction == null) {
            transaction = manager.beginTransaction();
        }

        fragmentable = getItem(position);
        String tagName = getTagName(position);
        if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragmentable);
        if (savedStateList.size() > position) {
            if (tagNameList.contains(tagName)){
                Fragment.SavedState fss = savedStateList.get(position);
                if (fss != null) {
                    fragmentable.setInitialSavedState(fss);
                }
            }
        }

        while (fragmentableList.size() <= position) {
            fragmentableList.add(null);
        }

        fragmentable.setMenuVisibility(false);

        fragmentableList.set(position, fragmentable);
        transaction.add(container.getId(), fragmentable, tagName);

        if (behavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            transaction.setMaxLifecycle(fragmentable, Lifecycle.State.STARTED);
        }

        return fragmentable;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;

        if (transaction == null) {
            transaction = manager.beginTransaction();
        }

        if (DEBUG) Log.v(TAG, "Removing item #" + position + ": f=" + object + " v=" + ((Fragmentable)object).getView());

        while (savedStateList.size() <= position) {
            savedStateList.add(null);
            tagNameList.add(null);
        }

        savedStateList.set(position, fragmentable.isAdded() ? manager.saveFragmentInstanceState(fragmentable) : null);
        tagNameList.set(position, fragmentable.isAdded() ? fragmentable.getPaginable().getTagName() : null);
        fragmentableList.set(position, null);

        transaction.remove(fragmentable);
        if (this.fragmentable.equals(fragmentable)) {
            this.fragmentable = null;
        }
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;
        if (this.fragmentable != fragmentable) {
            if (this.fragmentable != null) {
                this.fragmentable.setMenuVisibility(false);
                if (behavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    if (transaction == null) {
                        transaction = manager.beginTransaction();
                    }
                    transaction.setMaxLifecycle(this.fragmentable, Lifecycle.State.STARTED);
                } else {
                    this.fragmentable.setUserVisibleHint(false);
                }
            }
            fragmentable.setMenuVisibility(true);
            if (behavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                if (transaction == null) {
                    transaction = manager.beginTransaction();
                }
                transaction.setMaxLifecycle(fragmentable, Lifecycle.State.RESUMED);
            } else {
                fragmentable.setUserVisibleHint(true);
            }

            this.fragmentable = fragmentable;
        }
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        if (transaction != null) {
            try {
                transaction.commitNowAllowingStateLoss();
            } catch (IllegalStateException e) {
                // Workaround for Robolectric running measure/layout
                // calls inline rather than allowing them to be posted
                // as they would on a real device.
                transaction.commitAllowingStateLoss();
            }
            transaction = null;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((Fragment)object).getView() == view;
    }

    @Override
    @Nullable
    public Parcelable saveState() {
        Bundle state = null;
        if (savedStateList.size() > 0) {
            state = new Bundle();
            state.putStringArrayList(LIST, tagNameList);
            Fragment.SavedState[] fss = new Fragment.SavedState[savedStateList.size()];
            savedStateList.toArray(fss);
            state.putParcelableArray("states", fss);
        }
        for (int i=0; i<fragmentableList.size(); i++) {
            Fragment f = fragmentableList.get(i);
            if (f != null && f.isAdded()) {
                if (state == null) {
                    state = new Bundle();
                }
                String key = "f" + i;
                manager.putFragment(state, key, f);
            }
        }
        return state;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        if (state != null) {
            Bundle bundle = (Bundle)state;
            bundle.setClassLoader(loader);
            tagNameList = bundle.getStringArrayList(LIST);
            Parcelable[] fss = bundle.getParcelableArray("states");
            savedStateList.clear();
            fragmentableList.clear();
            if (fss != null) {
                for (Parcelable parcelable : fss) {
                    savedStateList.add((Fragment.SavedState) parcelable);
                }
            }
            Iterable<String> keys = bundle.keySet();
            for (String key: keys) {
                if (key.startsWith("f")) {
                    int index = Integer.parseInt(key.substring(1));
                    Fragmentable f = (Fragmentable) manager.getFragment(bundle, key);
                    if (f != null) {
                        while (fragmentableList.size() <= index) {
                            fragmentableList.add(null);
                        }
                        f.setMenuVisibility(false);
                        fragmentableList.set(index, f);
                    } else {
                        Log.w(TAG, "Bad fragment at key " + key);
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPaginable(position).getPageTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable fragmentable = (Fragmentable) object;
        Paginable paginable = fragmentable.getPaginable();
        int position = getPaginableList().indexOf(paginable);
        if (position != Base.INVALID_POSITION){
            return position;
        }
        if (getCount() == 0){
            listener.onChangePosition(position);
        }
        return POSITION_NONE;
    }

    // PRIVATE METHODS =============================================================================

    private Paginable getPaginable(int position){
        return getPaginableList().get(position);
    }

    private ArrayList<Paginable> getPaginableList(){
        return listable.getList();
    }
}
