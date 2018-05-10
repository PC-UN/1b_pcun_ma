package com.pcun.b1.a1b_pcun_ma;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AllPointsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public final String TAG = "debug_lines";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_all_points);
        EditText filter_text = (EditText) findViewById(R.id.search_filter);

        Log.d(TAG, "Descargando todos los puntos... (aprox 9500 puntos)... esperar...");
        final PointConnection pointConnection =
                new PointConnection();
        pointConnection.allPoints(this);

        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pointConnection.getPointAdapter().getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onButtonClick(View view) {
        /*
            The true branch of following sentence validates that
            user be logged in app.
        */
        if(((GlobalData) this.getApplication()).getSessionToken() != "") {
            AuthConnection authConnection = new AuthConnection();
            authConnection.checkSession(((GlobalData) this.getApplication()).getSessionToken(), this);
            while (((GlobalData) this.getApplication()).isSessionVerified()) ;
            Intent intent = new Intent(this, CreatePointForm.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Debes iniciar sesión para crear un punto.", Toast.LENGTH_SHORT).show();
        }
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
