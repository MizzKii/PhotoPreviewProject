package com.mizzkii.photoproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mizzkii.photoproject.R;
import com.mizzkii.photoproject.adapter.PhotoListAdapter;

/**
 * Created by MizzKii on 9/5/2016 AD.
 */
public class GridFragment extends Fragment {

    GridView gridView;
    PhotoListAdapter listAdapter;

    public static GridFragment newInstance() {

        Bundle args = new Bundle();

        GridFragment fragment = new GridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        listAdapter = new PhotoListAdapter();
        gridView.setAdapter(listAdapter);
    }
}
