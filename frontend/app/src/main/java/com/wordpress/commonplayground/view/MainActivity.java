package com.wordpress.commonplayground.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.network.PostMessagePollRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        setUpUIElements();
        pollMessages();
    }

    private void pollMessages() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userID", session.getUserDetails().get(SessionManager.KEY_ID));

        PostMessagePollRequest request = new PostMessagePollRequest(this);
        request.stringRequest("hasNewMessages", "Login", parameters, findViewById(R.id.drawer_layout));
    }

    private void setUpUIElements() {
        setUpToolbarAndDrawer();
        setUpNavigation();
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
        HashMap<String, String> user = session.getUserDetails();
        String menuItem = user.get(SessionManager.KEY_MENU_ITEM_MAIN);
        int idMenuItem;
        MenuItem navItem;
        if ("-1".equals(menuItem) || menuItem == null) {
            navItem = navigationView.getMenu().findItem(R.id.nav_dashboard);
            idMenuItem = -1;
        } else {
            idMenuItem = Integer.parseInt(menuItem);
            navItem = navigationView.getMenu().findItem(idMenuItem);
        }
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navItem);
        navigationView.setCheckedItem(idMenuItem);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        session.checkLogin();
        pollMessages();
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
        if (id == R.id.action_search) {
            Intent openSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(openSearchActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();
        session.setKeyMenuItemMain("" + id);

        if (id == R.id.nav_mysessions) {
            fragment = new MySessionsFragment();
            setTitle(R.string.menu_mySessions);
        } else {
            MySessionsFragment.resetTabPostition();
        }

        if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
            setTitle(R.string.menu_dashboard);

        } else if (id == R.id.nav_messages) {
            fragment = new MessageFragment();
            setTitle(R.string.menu_messages);

        //} else if (id == R.id.nav_profile) {
            //setTitle(R.string.menu_profile);
        //} else if (id == R.id.nav_friendlist) {
            //setTitle(R.string.menu_friendlist);
        } else if (id == R.id.nav_logout) {
            session.logoutUser();
        //} else if (id == R.id.nav_faq) {
            //setTitle(R.string.menu_faq);
        //} else if (id == R.id.nav_contactAdmin) {
            //setTitle(R.string.menu_contactAdmin);
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.screen_area, fragment);

            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}