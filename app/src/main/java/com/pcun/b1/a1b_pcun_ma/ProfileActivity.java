package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_profile);

        Button contin = (Button) findViewById(R.id.mis_favoritos);
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBusesStatusActivity = new Intent(getApplicationContext(), FavouriteActivity.class);
                startActivity(intentBusesStatusActivity);
            }
        });
        TextView textUser = (TextView)findViewById(R.id.userNAME);
        //textUser.setText("Angela");
        //textUser.setText(((GlobalData) UserConnection.checkSession(context.getApplication()).getCurrentUserName()));
        textUser.setText(UserConnection.currentU(this));


    }

}
