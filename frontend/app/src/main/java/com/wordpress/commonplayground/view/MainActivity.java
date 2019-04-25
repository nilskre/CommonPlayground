package com.wordpress.commonplayground.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MainActivityViewModel mainActivityViewModel;
    private SessionsAdapter adapter;
    private RecyclerView rvSessions;
    private SessionManager session;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeChangesInSessionList();
                Snackbar.make(rvSessions, R.string.refreshed, 2000).show();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryLight,
                R.color.colorAccent);

        setUpUIElements();
        VolleyRequestQueue.getInstance(this);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        observeChangesInSessionList();
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
    }

    private void setUpUIElements() {
        setUpToolbarAndDrawer();
        setUpNavigation();
        setUpFab();
        setUpRecyclerView();
    }

    private void setUpToolbarAndDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpNavigation() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openAddSessionActivity = new Intent(MainActivity.this, AddSessionActivity.class);
                startActivity(openAddSessionActivity);
            }
        });
    }

    private void setUpRecyclerView() {
        rvSessions = findViewById(R.id.rvSessions);
    }


    private void observeChangesInSessionList() {
        mainActivityViewModel.getSessions("getSessionList").observe(this, new android.arch.lifecycle.Observer<List<Session>>() {
            @Override
            public void onChanged(@Nullable List<Session> sessions) {
                Log.d("Observed: ", "SessionList changed");
                updateAndDisplayListData(sessions);
            }
        });
    }

    private void updateAndDisplayListData(List<Session> sessions) {
        adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        session.checkLogin();
        mainActivityViewModel.getSessions("getSessionList");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_mysessions) {
            Intent openMySessionsActivity = new Intent(MainActivity.this, MySessionsActivity.class);
            startActivity(openMySessionsActivity);
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_friendlist) {

        } else if (id == R.id.nav_logout) {
            session.logoutUser();
        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_contactAdmin) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}