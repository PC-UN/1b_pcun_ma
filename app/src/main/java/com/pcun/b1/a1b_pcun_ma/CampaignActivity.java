package com.pcun.b1.a1b_pcun_ma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.content.Intent;

//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

//import android.view.MenuItem;
//import android.widget.Toast;



public class CampaignActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_campaign);

        mDrawerLayout = findViewById(R.id.nav_campaign;
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




}


