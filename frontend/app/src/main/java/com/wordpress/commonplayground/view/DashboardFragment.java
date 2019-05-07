package com.wordpress.commonplayground.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.List;

public class DashboardFragment extends Fragment {
    private SwipeRefreshLayout swipeContainer;
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView rvSessions;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        setUpSwipeToRefresh();
        rvSessions = view.findViewById(R.id.rvSessions);
        setUpFab();
        VolleyRequestQueue.getInstance(getContext());
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        observeChangesInSessionList();
        SessionManager session = new SessionManager(getContext());
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
        mainActivityViewModel.getSessions("getSessionList").observe(this, new android.arch.lifecycle.Observer<List<Session>>() {
            @Override
            public void onChanged(@Nullable List<Session> sessions) {
                Log.d("Observed: ", "SessionList changed");
                updateAndDisplayListData(sessions);
            }
        });
    }

    private void updateAndDisplayListData(List<Session> sessions) {
        SessionsAdapter adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpFab() {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openAddSessionActivity = new Intent(getContext(), AddSessionActivity.class);
                startActivity(openAddSessionActivity);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setReorderingAllowed(false);
        ft.detach(this).attach(this).commit();
    }
}