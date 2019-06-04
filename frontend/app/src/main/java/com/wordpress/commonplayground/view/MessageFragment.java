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
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MessageViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.List;

public class MessageFragment extends Fragment {
    private SwipeRefreshLayout swipeContainer;
    private MessageViewModel messageViewModel;
    private RecyclerView rvMessage;
    private View view;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        setUpSwipeToRefresh();
        rvMessage = view.findViewById(R.id.recyclerView);
        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpFab();
        VolleyRequestQueue.getInstance(getContext());
        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        session = new SessionManager(getContext());
        observeChangesInMessageList();
    }

    private void setUpSwipeToRefresh() {
        swipeContainer = view.findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeChangesInMessageList();
                Snackbar.make(rvMessage, R.string.refreshed, 2000).show();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryLight,
                R.color.colorAccent);
    }

    public void observeChangesInMessageList() {
        messageViewModel.getMessage(session.getUserDetails().get(SessionManager.KEY_ID)).observe(this, new android.arch.lifecycle.Observer<List<?>>() {
            @Override
            public void onChanged(@Nullable List<?> inbox) {
                Log.d("Observed: ", "Inbox changed");
                updateAndDisplayListData(inbox);
            }
        });
    }

    private void updateAndDisplayListData(List<?> inbox) {
        MessagesAdapter adapter = new MessagesAdapter(inbox, session, messageViewModel, rvMessage);
        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpFab() {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSendMessageActivity = new Intent(getContext(), SendMessageActivity.class);
                startActivity(openSendMessageActivity);
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