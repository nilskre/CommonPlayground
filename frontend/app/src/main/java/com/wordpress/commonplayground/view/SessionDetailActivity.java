package com.wordpress.commonplayground.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.model.User;

import java.util.ArrayList;
import java.util.List;

public class SessionDetailActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<Session> sessionList;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Bundle extras = getIntent().getExtras();
        sessionList = extras.getParcelableArrayList("Sessions");
        index = extras.getInt("Index");

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
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

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Session session) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SESSION_TITLE, session.getTitle());

            List<User> users = new ArrayList<>();
            try {                                                                                               //TODO
                users.addAll(session.getUsers());
                for(User user : users) {
                    Log.v("Helau", user.toString());
                }
                Log.v("Helau", session.toString());
                args.putString(ARG_SESSION_HOST, users.get(0).getName());
            } catch (Exception e) {
                Log.v("ALOHA", e.getMessage());
                args.putString(ARG_SESSION_HOST, "Could not get id of host");
            }
            String aloha = "";
            for(User u : users) {
                aloha += u.getName();
            }
            Log.v("ALOHA", aloha + "hello");

            args.putString(ARG_SESSION_GAME, session.getGame());
            args.putString(ARG_SESSION_GENRE, "Genre");                                                         //TODO
            args.putString(ARG_SESSION_TYPE, "Online/Offline");                                                 //TODO
            args.putString(ARG_SESSION_PLACE, session.getPlace());
            args.putString(ARG_SESSION_DATE, session.getDate());
            args.putString(ARG_SESSION_TIME, session.getTime());

            if(!session.getUsers().equals(null)) {                                                                 //TODO
                args.putString(ARG_SESSION_NUMBER_OF_PLAYERS, session.getUsers().size() + "/" + session.getNumberOfPlayers() + " players");
            } else {
                args.putString(ARG_SESSION_NUMBER_OF_PLAYERS, session.getNumberOfPlayers() + " players");
            }

            args.putString(ARG_SESSION_DESCRIPTION, session.getDescription());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_session_detail, container, false);
            TextView title = rootView.findViewById(R.id.session_title);
            title.setText(getArguments().getString(ARG_SESSION_TITLE));
            TextView host = rootView.findViewById(R.id.session_host);
            host.setText(getArguments().getString(ARG_SESSION_HOST));
            TextView game = rootView.findViewById(R.id.session_game);
            game.setText(getArguments().getString(ARG_SESSION_GAME));
            TextView genre = rootView.findViewById(R.id.session_genre);
            genre.setText(getArguments().getString(ARG_SESSION_GENRE));
            TextView type = rootView.findViewById(R.id.session_type);
            type.setText(getArguments().getString(ARG_SESSION_TYPE));
            TextView place = rootView.findViewById(R.id.session_place);
            place.setText(getArguments().getString(ARG_SESSION_PLACE));
            TextView date = rootView.findViewById(R.id.session_date);
            date.setText(getArguments().getString(ARG_SESSION_DATE));
            TextView time = rootView.findViewById(R.id.session_time);
            time.setText(getArguments().getString(ARG_SESSION_TIME));
            TextView numberOfPlayers = rootView.findViewById(R.id.session_number_of_players);
            numberOfPlayers.setText(getArguments().getString(ARG_SESSION_NUMBER_OF_PLAYERS));
            TextView description = rootView.findViewById(R.id.session_description);
            description.setText(getArguments().getString(ARG_SESSION_DESCRIPTION));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class above).
            return PlaceholderFragment.newInstance(sessionList.get(position));
        }

        @Override
        public int getCount() {
            return sessionList.size();
        }
    }
}