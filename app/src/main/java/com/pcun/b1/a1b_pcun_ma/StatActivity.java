package com.pcun.b1.a1b_pcun_ma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_stat);

        DisposalPointConnection disposalPointConnection = new DisposalPointConnection();
        disposalPointConnection.peoplePerDisposal(this);
    }
}
