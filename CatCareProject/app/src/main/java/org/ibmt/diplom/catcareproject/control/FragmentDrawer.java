package org.ibmt.diplom.catcareproject.control;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anna.catcareproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrawer extends Fragment {
    private static String TAG = FragmentDrawer.class.getSimpleName();
    private ListView listViewDrawer;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    //    private View containerView;
    private static String[] titlesDrawer = null;
    private FragmentDrawerListener drawerListener;
    private CharSequence titleDrawer;


    public static String[] getTitlesDrawer() {
        return titlesDrawer;
    }

    public FragmentDrawer() {
        // Required empty public constructor
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

//@Override
//public void onAttach(Context context) {
//    super.onAttach(context);
//    // This makes sure that the container activity has implemented the callback interface.
//    try {
//        drawerListener = (FragmentDrawerListener) context;
//    } catch (ClassCastException e) {
//        throw new ClassCastException(context.toString()
//                + " must implement FragmentChangeListener");
//    }
//}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // drawer labels
        titlesDrawer = getResources().getStringArray(R.array.menuTitles_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        titleDrawer = getActivity().getTitle();
        listViewDrawer = (ListView) layout.findViewById(R.id.drawerList);
        listViewDrawer.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.drawer_list_item, titlesDrawer));
        listViewDrawer.setOnItemClickListener(new DrawerItemClickListener());

        return layout;
    }


    public void setUp(DrawerLayout drawerLayout) {
//        containerView = getActivity().findViewById(fragmentId);
        this.drawerLayout = drawerLayout;

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        toggle = new ActionBarDrawerToggle(
                getActivity(),                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
//                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getActivity().getActionBar().setTitle(titleDrawer);
                getActivity().invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().getActionBar().setTitle(titleDrawer);
                getActivity().invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

//        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                toggle.syncState();
            }
        });
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            drawerListener.onDrawerItemSelected(view, position);
            // update selected item and title, then close the drawer
            listViewDrawer.setItemChecked(position, true);
            // set the toolbar title
//            getActivity().setTitle(titlesDrawer[position]);
            drawerLayout.closeDrawer(getActivity().findViewById(R.id.fragment_navigation_drawer));
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
