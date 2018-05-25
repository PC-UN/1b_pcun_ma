package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import android.widget.Toast;

public class CampaignActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_all_campaign);

        mDrawerLayout = findViewById(R.id.nav_all_campaign);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Toast.makeText(this, "this is acc", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_map) {
            Intent intent = new Intent(this, FragmentMapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_favorito) {
            Intent intent = new Intent(this, FavouriteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_all_campaign) {
            Intent intent = new Intent(this, AllCampaignActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_allpoints) {
            Intent intent = new Intent(this, AllPointsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_estadisticas) {
            Intent intent = new Intent(this, StatActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.mapf_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}


