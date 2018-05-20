package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class StatActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_stat);

        DisposalPointConnection disposalPointConnection = new DisposalPointConnection();
        disposalPointConnection.peoplePerDisposal(this);

        String token = ((GlobalData) this.getApplication()).getSessionToken();
        AuthConnection authConnection = new AuthConnection();
        authConnection.checkSession(token,this);
    }

}
