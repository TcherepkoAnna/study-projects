package org.ibmt.diplom.catcareproject.control;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import org.ibmt.diplom.catcareproject.Consts;

import com.example.anna.catcareproject.R;

import org.ibmt.diplom.catcareproject.dao.CatHelper;
import org.ibmt.diplom.catcareproject.model.Cat;
import org.ibmt.diplom.catcareproject.view.FragmentAbout;
import org.ibmt.diplom.catcareproject.view.FragmentAddCat;
import org.ibmt.diplom.catcareproject.view.FragmentAddFood;
import org.ibmt.diplom.catcareproject.view.FragmentAddRate;
import org.ibmt.diplom.catcareproject.view.FragmentAddMeal;
import org.ibmt.diplom.catcareproject.view.FragmentContacts;
import org.ibmt.diplom.catcareproject.view.FragmentGetReport;
import org.ibmt.diplom.catcareproject.view.FragmentLabTests;
import org.ibmt.diplom.catcareproject.view.adapter.PetAdapterDrawer;

import java.awt.Menu;
import java.util.List;

public class MainActivity extends FragmentActivity implements Consts, FragmentChangeListener, FragmentDrawer.FragmentDrawerListener {

    //    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private FragmentDrawer drawerFragment;
    DrawerLayout mDrawerLayout;

    Cat petGlobal = null;
    List<Cat> catsToDisplay;
    Spinner spCats;
    CatHelper cHelper;
    PetAdapterDrawer petAdapterDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        int MY_PERMISSIONS_REQUEST = 10;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        spCats = (Spinner) findViewById(R.id.sp_drawer_cat);
        //getting list of cats
        cHelper = new CatHelper(this);
        catsToDisplay = cHelper.getAllCats();
        petAdapterDrawer = new PetAdapterDrawer(this, catsToDisplay);
        spCats.setAdapter(petAdapterDrawer);
        //retrieving selected cat
        spCats.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    petAdapterDrawer.clear();
                    catsToDisplay = cHelper.getAllCats();
                    petAdapterDrawer.addAll(catsToDisplay);
                }
                return false;
            }
        });
        spCats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petGlobal = catsToDisplay.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mTitle = getTitle();
//        mTitle = getTitle() + " : " + petGlobal.getName();


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout));
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        if (savedInstanceState == null) {
            displayView(0);
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // enable toolbar action items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.

//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        // Handle action buttons

        switch (item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.home:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    petAdapterDrawer.clear();
                    catsToDisplay = cHelper.getAllCats();
                    petAdapterDrawer.addAll(catsToDisplay);
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
            default:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    petAdapterDrawer.clear();
                    catsToDisplay = cHelper.getAllCats();
                    petAdapterDrawer.addAll(catsToDisplay);
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = "";

        switch (position) {
            case 0:
                fragment = new FragmentAddCat();
//                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FragmentAddMeal();
                break;
            case 2:
                fragment = new FragmentAddFood();
                break;
            case 3:
                fragment = new FragmentAddRate();
                break;
            case 4:
                fragment = new FragmentLabTests();
                break;
            case 5:
                fragment = new FragmentGetReport();
                break;
            case 6:
                fragment = new FragmentAbout();
                break;
            case 7:
                fragment = new FragmentContacts();
                break;
            case 8:
                fragment = new FragmentAbout();
                break;
            default:
                fragment = new FragmentAbout();
        }

        if (fragment != null) {
            if (petGlobal != null) {
                Bundle args = new Bundle();
                args.putLong(GLOBAL_PET, petGlobal.getId());
                fragment.setArguments(args);
            }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            fragmentTransaction.replace(R.id.content_frame, fragment).commit();

            title = drawerFragment.getTitlesDrawer()[position];
            // set the toolbar title
            setTitle(title);

        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public void changeToUpdate(long newRowId, Fragment someFragment) {

        Fragment newFragment = someFragment;

        Bundle args = new Bundle();
//        args.putParcelable(SOME_CAT_ARGS, cat);
        args.putLong(SOME_FRAG_ARGS, newRowId);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment, and add the transaction to the back stack
        transaction.replace(R.id.content_frame, newFragment).addToBackStack(null).commit();

    }


}