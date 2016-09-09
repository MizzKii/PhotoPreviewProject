package com.mizzkii.photoproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mizzkii.photoproject.R;
import com.mizzkii.photoproject.adapter.PhotoListAdapter;

/**
 * Created by MizzKii on 9/3/2016 AD.
 */
public class ViewListFragment extends Fragment {

    private ListView listView;

    public static ViewListFragment newInstance() {

        Bundle args = new Bundle();

        ViewListFragment fragment = new ViewListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(final View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(PhotoListAdapter.getInstance());
    }
}