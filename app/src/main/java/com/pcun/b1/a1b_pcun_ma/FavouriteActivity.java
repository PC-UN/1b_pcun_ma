package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    public final String TAG = "debug_lines";
    //int id = FavoriteConnection.favoriteById(this,((GlobalData) FavouriteActivity.getApplication()).getCurrentUser());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_favourite);

        EditText filter_text = (EditText) findViewById(R.id.search_filter);

        Log.d(TAG, "Puntos favoritos ");
        final FavoriteConnection favoriteConnection =
                new FavoriteConnection();
        //favoriteConnection.favoriteById(((GlobalData) this.getApplication()).getCurrentUser(), this);
        favoriteConnection.favoriteById(5,this);

        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                favoriteConnection.getFavoriteAdapter().getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
