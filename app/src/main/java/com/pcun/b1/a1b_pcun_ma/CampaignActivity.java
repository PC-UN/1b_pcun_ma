package com.pcun.b1.a1b_pcun_ma;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CampaignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_campaign);

        PointConnection pointConnection = new PointConnection();
        pointConnection.allPoints(this);
    }
}
