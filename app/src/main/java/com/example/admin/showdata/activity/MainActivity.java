package com.example.admin.showdata.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.showdata.apiService.ImageLoadTask;
import com.example.admin.showdata.fragment.ProductDetailFragment;
import com.example.admin.showdata.fragment.ProductListFragment;
import com.example.admin.showdata.fragment.ProfileFragment;
import com.example.admin.showdata.R;
import com.example.admin.showdata.fragment.RootFragment;
import com.example.admin.showdata.model.ContentModel;

public class MainActivity extends AppCompatActivity implements ProductListFragment.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
            fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(fragmentPagerAdapter);
        }

        // setting image from url in header
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) header.findViewById(R.id.profile_image);

        new ImageLoadTask(imageView, MainActivity.this, header.findViewById(R.id.profile_image_loader)).execute("http://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/sample1_l.jpg");

        // get id of drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);

        // action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        //setting menu button on action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.getTitle();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings_button:
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(ContentModel contentModel) {
        // load the product details Fragment
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();

        //communicating with fragment using bundle
        Bundle bundle = new Bundle();
        bundle.putParcelable("Product", contentModel);
        productDetailFragment.setArguments(bundle);

        // replacing fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, productDetailFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 2;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RootFragment();
                case 1:
                    return new ProfileFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Feed";
                case 1:
                    return "Profile";
                default:
                    return null;
            }
        }
    }
}
