package com.example.koracorner;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {








    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;



    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);









































        //Get FireBase auth instance
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fram
                        , new Main())
                .commit();


        //Actionbar
        ActionBar actionBar = getSupportActionBar();
        //set title
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settingsis selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Main())
                    .commit();

        } else if (id == R.id.nav_channel) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Channel())
                    .commit();
            setTitle("Channel");

        }
        else if (id == R.id.nav_table) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Table())
                    .commit();
            setTitle("Table");

        }else if (id == R.id.nav_account) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Account())

                    .commit();
            setTitle("Account");

        }else if (id == R.id.nav_video) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Video())
                    .commit();
            setTitle("Video");

        } else if (id == R.id.nav_chat) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Chat())
                    .commit();
            setTitle("Chat");
        }else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Contact())
                    .commit();
            setTitle("Contact us");
        }else if (id == R.id.nav_share) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fram
                            , new Share())
                    .commit();
            setTitle("Share");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.fram, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    private void addNotification() {
        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Test")
                .setContentText("TEEEEEEESSSSSSSSST");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


























    @Override
    protected void onResume() {

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
finish();
        }
        super.onResume();
    }



}





