package com.pcun.b1.a1b_pcun_ma;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class AllPointsActivity extends AppCompatActivity{
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
        if(UserConnection.checkSession(this) > 0) {
            Intent intent = new Intent(this, CreatePointForm.class);
            startActivity(intent);
        }
    }

}
