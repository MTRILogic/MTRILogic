package com.mtrilogic.mtrilogic.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Paginable;
import com.mtrilogic.interfaces.PaginableItemListener;
import com.mtrilogic.mtrilogic.R;
import com.mtrilogic.mtrilogic.databinding.FragmentDefaultBinding;
import com.mtrilogic.mtrilogic.pages.DefaultPage;

@SuppressWarnings("unused")
public class DefaultPaginable extends Paginable<DefaultPage> {

    private FragmentDefaultBinding binding;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public DefaultPaginable(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, @NonNull PaginableItemListener listener) {
        super(DefaultPage.class, FragmentDefaultBinding.inflate(inflater, parent, false).getRoot(), listener);
    }

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onBindItemView() {
        binding = FragmentDefaultBinding.bind(itemView);
    }

    @Override
    public void onBindPage() {
        binding.lblTitle.setText(itemView.getContext().getString(R.string.default_page, page.getItemId(), position));
    }
}
