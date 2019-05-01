package com.wordpress.commonplayground.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wordpress.commonplayground.R;
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
        int idMenuItem = Integer.parseInt(user.get(SessionManager.KEY_MENU_ITEM_MAIN));
        MenuItem navItem;
        if (idMenuItem == -1) {
            navItem = navigationView.getMenu().findItem(R.id.nav_dashboard);
        } else {
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
        Fragment fragment = null;

        int id = item.getItemId();
        session.setKeyMenuItemMain("" + id);

        if (id == R.id.nav_mysessions) {
            fragment = new MySessionsFragment();
        } else {
            MySessionsFragment.resetTabPostition();
        }

        if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_friendlist) {

        } else if (id == R.id.nav_logout) {
            session.logoutUser();
        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_contactAdmin) {

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