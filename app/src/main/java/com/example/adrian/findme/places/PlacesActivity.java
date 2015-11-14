package com.example.adrian.findme.places;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.adrian.findme.R;
import com.example.adrian.findme.places.categories.CategoriesFragment;
import com.example.adrian.findme.places.favourites.FavouritesFragment;
import com.example.adrian.findme.places.featured.FeaturedFragment;
import com.example.adrian.findme.places.most.popular.MostPopularFragment;
import com.example.adrian.findme.places.neww.NewFragment;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class PlacesActivity extends AppCompatActivity {

    /**
     * Navigation Drawer Variables
     */
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private CharSequence mActivityTitle;

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        mActivityTitle = "Places";

        // Initialize toolbar
        setupToolbar();

        // Initialize tabs
        setupTabLayout();

        // Initialize viewpager
        setupViewpager();

        // Initialize navigation drawer
        setupNavDrawer();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mActivityTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupTabLayout(){
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setupViewpager() {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PlacesPagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_places);

        // Set navigation drawer icon
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Set current item as checked
        Menu menu = mNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.navigation_places);
        menuItem.setChecked(true);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
//                    case R.id.navigation_events:
//                        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.navigation_meetup:
//                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places, menu);
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


    private static class PlacesPagerAdapter extends FragmentPagerAdapter {

        private static final String[] TITLES = new String[]{"Categories", "Featured", "New",
                "Most Popular", "Favourites"};

        public PlacesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CategoriesFragment();
                case 1:
                    return new FeaturedFragment();
                case 2:
                    return new NewFragment();
                case 3:
                    return new MostPopularFragment();
                case 4:
                    return new FavouritesFragment();
                default:
                    return new FeaturedFragment();
            }
        }

        @Override
        public float getPageWidth(int position) {
            if (position == 0)
                return 0.4f;
            else
                return 1f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
}
