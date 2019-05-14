package com.wordpress.commonplayground.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.model.User;
import com.wordpress.commonplayground.network.PostJoinRequest;
import com.wordpress.commonplayground.network.PostLeaveRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class SessionDetailActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private List<Session> sessionList;
    private SessionManager credentials;
    private Resources r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_detail);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        credentials = new SessionManager(this);

        Bundle extras = getIntent().getExtras();
        sessionList = extras.getParcelableArrayList("Sessions");
        int index = extras.getInt("Index");

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(index);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SESSION_isHOST = "isHost";
        private static final String ARG_SESSION_canLeave = "canLeave";
        private static final String ARG_SESSION_UID = "uID";
        private static final String ARG_SESSION_SID = "sID";
        private static final String ARG_SESSION_TITLE = "title";
        private static final String ARG_SESSION_HOST = "host";
        private static final String ARG_SESSION_GAME = "game";
        private static final String ARG_SESSION_GENRE = "genre";
        private static final String ARG_SESSION_TYPE = "type";
        private static final String ARG_SESSION_PLACE = "place";
        private static final String ARG_SESSION_DATE = "date";
        private static final String ARG_SESSION_TIME = "time";
        private static final String ARG_SESSION_NUMBER_OF_PLAYERS = "numberOfPlayers";
        private static final String ARG_SESSION_DESCRIPTION = "description";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Session session, String uID) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SESSION_TITLE, session.getTitle());

            ArrayList<User> users = new ArrayList<>();
            try {
                users.addAll(session.getUsers());
                args.putString(ARG_SESSION_HOST, users.get(0).getName());
            } catch (Exception e) {
                Log.v("Host_ID", e.getMessage());
                args.putString(ARG_SESSION_HOST, "Could not get id of host");
            }

            args.putString(ARG_SESSION_UID, uID);
            args.putString(ARG_SESSION_SID, Long.toString(session.getId()));
            args.putString(ARG_SESSION_GAME, session.getGame());
            args.putString(ARG_SESSION_GENRE, session.getGenre());
            args.putString(ARG_SESSION_TYPE, session.getType());
            args.putString(ARG_SESSION_PLACE, session.getPlace());
            args.putString(ARG_SESSION_DATE, session.getDate());
            args.putString(ARG_SESSION_TIME, session.getTime());
            args.putString(ARG_SESSION_NUMBER_OF_PLAYERS, users.size() + "/" + session.getNumberOfPlayers() + " players");
            args.putString(ARG_SESSION_DESCRIPTION, session.getDescription());

            boolean canLeave = (isPending(uID, session) || hasJoined(uID, session));
            boolean isHost = isHost(uID, session);
            args.putBoolean(ARG_SESSION_isHOST, isHost);
            args.putBoolean(ARG_SESSION_canLeave, canLeave);

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();
            View rootView = inflater.inflate(R.layout.fragment_session_detail, container, false);
            TextView title = rootView.findViewById(R.id.session_title);
            title.setText(args.getString(ARG_SESSION_TITLE));
            TextView host = rootView.findViewById(R.id.session_host);
            host.setText(args.getString(ARG_SESSION_HOST));
            TextView game = rootView.findViewById(R.id.session_game);
            game.setText(args.getString(ARG_SESSION_GAME));
            TextView genre = rootView.findViewById(R.id.session_genre);
            genre.setText(args.getString(ARG_SESSION_GENRE));
            TextView type = rootView.findViewById(R.id.session_type);
            type.setText(args.getString(ARG_SESSION_TYPE));
            TextView place = rootView.findViewById(R.id.session_place);
            place.setText(args.getString(ARG_SESSION_PLACE));
            TextView date = rootView.findViewById(R.id.session_date);
            date.setText(args.getString(ARG_SESSION_DATE));
            TextView time = rootView.findViewById(R.id.session_time);
            time.setText(args.getString(ARG_SESSION_TIME));
            TextView numberOfPlayers = rootView.findViewById(R.id.session_number_of_players);
            numberOfPlayers.setText(args.getString(ARG_SESSION_NUMBER_OF_PLAYERS));
            TextView description = rootView.findViewById(R.id.session_description);
            description.setText(args.getString(ARG_SESSION_DESCRIPTION));
            Button joinButton = rootView.findViewById(R.id.ButtonJoinSession);
            Button leaveButton = rootView.findViewById(R.id.ButtonLeaveSession);
            setUpButtons(joinButton, leaveButton, args.getBoolean("isHost"), args.getBoolean("canLeave"), args.getString("uID"), args.getString("sID"), rootView, getActivity(), getActivity().getResources());
            return rootView;
        }
    }

    private static void setUpButtons(Button joinButton, Button leaveButton, boolean isHost, boolean canLeave, String uID, String sID, View view, Context context, Resources r) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userID", uID);
        parameters.put("sessionID", sID);

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostLeaveRequest request = new PostLeaveRequest(r);
                request.stringRequest("leaveSession", "LeaveRequest", context, parameters, view);
                leaveButton.setVisibility(GONE);
                joinButton.setVisibility(View.VISIBLE);
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostJoinRequest request = new PostJoinRequest(r);
                request.stringRequest("joinRequestForSession", "JoinRequest", context, parameters, view);
                joinButton.setVisibility(GONE);
                leaveButton.setVisibility(View.VISIBLE);
            }
        });

        if (isHost) {
            leaveButton.setVisibility(GONE);
            joinButton.setVisibility(GONE);

        } else if (canLeave) {
            joinButton.setVisibility(GONE);
        } else {
            leaveButton.setVisibility(GONE);
        }
    }

    private static boolean isHost(String uID, Session session) {
        if (uID.equals(Long.toString(session.getIdOfHost()))) {
            return true;
        }
        return false;
    }

    private static boolean hasJoined(String uID, Session session) {
        if (session.getUsers().size() < 1) return false;
        boolean found = false;
        List<User> users = session.getUsers();
        for (User user : users) {
            if (Long.toString(user.getId()).equals(uID))
                found = true;
            break;
        }
        return found;
    }

    private static boolean isPending(String uID, Session session) {
        if (session.getUsersPending().size() < 1) return false;
        boolean found = false;
        List<User> users = session.getUsersPending();
        for (User user:users) {
            if (Long.toString(user.getId()).equals(uID))
                found=true;
            break;
        }
        return found;
    }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class above).
            return PlaceholderFragment.newInstance(sessionList.get(position), credentials.getUserDetails().get(SessionManager.KEY_ID));
        }

        @Override
        public int getCount() {
            return sessionList.size();
        }
    }
}