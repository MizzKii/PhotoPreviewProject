package com.mizzkii.photoproject.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mizzkii.photoproject.R;
import com.mizzkii.photoproject.adapter.PhotoListAdapter;
import com.mizzkii.photoproject.dao.PhotoItemCollectionDao;
import com.mizzkii.photoproject.fragment.GridListFragment;
import com.mizzkii.photoproject.fragment.ViewListFragment;
import com.mizzkii.photoproject.manager.Contextor;
import com.mizzkii.photoproject.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        initFragment(savedInstanceState);
    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //noinspection ConstantConditions
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ViewListFragment viewListFragment = ViewListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, viewListFragment, "ViewListFragment")
                    .commit();

            GridListFragment gridListFragment = GridListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, gridListFragment, "GridFragment")
                    .detach(gridListFragment)
                    .commit();

            loadPhotoList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        ViewListFragment viewListFragment;
        GridListFragment gridListFragment;
        switch (item.getItemId()) {
            case R.id.itemViewList:
                viewListFragment = (ViewListFragment) getSupportFragmentManager().findFragmentByTag("ViewListFragment");
                gridListFragment = (GridListFragment) getSupportFragmentManager().findFragmentByTag("GridFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(viewListFragment)
                        .detach(gridListFragment)
                        .commit();
                PhotoListAdapter.getInstance().setViewList(true);
                return true;
            case R.id.itemGridView:
                viewListFragment = (ViewListFragment) getSupportFragmentManager().findFragmentByTag("ViewListFragment");
                gridListFragment = (GridListFragment) getSupportFragmentManager().findFragmentByTag("GridFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(gridListFragment)
                        .detach(viewListFragment)
                        .commit();
                PhotoListAdapter.getInstance().setViewList(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPhotoList() {
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
                if (response.isSuccessful()) {
                    PhotoItemCollectionDao dao = response.body();
                    PhotoListAdapter.getInstance().setDao(dao);

                    PhotoListAdapter.getInstance().notifyDataSetChanged();

                    Toast.makeText(Contextor.getInstance().getContext(),
                            "Loaded Success",
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
