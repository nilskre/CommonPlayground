package com.wordpress.commonplayground.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.HashMap;
import java.util.List;

public class MySessionsFragment extends Fragment {
    private View view;
    private SwipeRefreshLayout swipeContainer;
    private MainActivityViewModel mainActivityViewModel;
    private SessionManager session;
    private RecyclerView rvSessions;
    private TabLayout tabLayout;
    private static int selectedTabPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_sessions, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        rvSessions = view.findViewById(R.id.rvSessions);
        VolleyRequestQueue.getInstance(getContext());
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        session = new SessionManager(getContext());
        setUpTabLayout();
        setUpSwipeToRefresh();
        observeChangesInSessionList();
    }

    private void setUpSwipeToRefresh() {
        swipeContainer = view.findViewById(R.id.swipeContainer);

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
    }

    private void observeChangesInSessionList() {
        HashMap<String, String> user = session.getUserDetails();
        String userID = user.get(SessionManager.KEY_ID);

        selectedTabPosition = tabLayout.getSelectedTabPosition();
        String api;
        if (selectedTabPosition == 0) {
            api = "getMyHostedSessions";
        } else if (selectedTabPosition == 1) {
            api = "getMyJoinedSessions";
        } else {
            api = "getMyPendingSessions";
        }

        mainActivityViewModel.getSessions(api + "?userID=" + userID).observe(this, new android.arch.lifecycle.Observer<List<?>>() {
            @Override
            public void onChanged(@Nullable List<?> sessions) {
                Log.d("Observed: ", "SessionList changed");
                updateAndDisplayListData(sessions);
            }
        });
    }

    private void updateAndDisplayListData(List<?> sessions) {
        SessionsAdapter adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpTabLayout() {
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                observeChangesInSessionList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //When a tab gets unselected, do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                observeChangesInSessionList();
            }
        });
        TabLayout.Tab tab = tabLayout.getTabAt(selectedTabPosition);
        tab.select();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setReorderingAllowed(false);
        ft.detach(this).attach(this).commit();
    }

    public static void resetTabPostition() {
        selectedTabPosition = 0;
    }
}