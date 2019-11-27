package com.hungrydroid.restaurantapp;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.hungrydroid.restaurantapp.fragments.CartFragment;
import com.hungrydroid.restaurantapp.fragments.ContactFragment;
import com.hungrydroid.restaurantapp.fragments.GalleryFragment;
import com.hungrydroid.restaurantapp.fragments.LocationFragment;
import com.hungrydroid.restaurantapp.fragments.MenuFragment;
import com.hungrydroid.restaurantapp.fragments.NewsFragment;
import com.hungrydroid.restaurantapp.fragments.ReserveFragment;
import com.hungrydroid.restaurantapp.fragments.SocialFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction ft;
    //TextView yourTextView = null;
    //Field f;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");
       /* try {
            f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            yourTextView = (TextView) f.get(toolbar);
            yourTextView.setText("Menu");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Setting menu default
        navigationView.setCheckedItem(R.id.nav_menu);
        navigationView.setItemIconTintList(null);
        //Loadng menu fragment
        // Begin the transaction
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_place, new MenuFragment());
        ft.commit();
        //



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_menu) {
            ft.replace(R.id.fragment_place, new MenuFragment());
            getSupportActionBar().setTitle("Menu");
        } else if (id == R.id.nav_cart) {
            ft.replace(R.id.fragment_place, new CartFragment());
            getSupportActionBar().setTitle("Cart");
        } else if (id == R.id.nav_reserve) {
            ft.replace(R.id.fragment_place, new ReserveFragment());
            getSupportActionBar().setTitle("Reservation");
        } else if (id == R.id.nav_gallery) {
            ft.replace(R.id.fragment_place, new GalleryFragment());
            getSupportActionBar().setTitle("Gallery");
        } else if (id == R.id.nav_news) {
            ft.replace(R.id.fragment_place, new NewsFragment());
            getSupportActionBar().setTitle("News");
        } else if (id == R.id.nav_location) {
            ft.replace(R.id.fragment_place, new LocationFragment());
            getSupportActionBar().setTitle("Location");
        } else if (id == R.id.nav_social) {
            ft.replace(R.id.fragment_place, new SocialFragment());
            getSupportActionBar().setTitle("Social");
        } else if (id == R.id.nav_contact) {
            ft.replace(R.id.fragment_place, new ContactFragment());
            getSupportActionBar().setTitle("Contact");
        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
