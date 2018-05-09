package com.pcun.b1.a1b_pcun_ma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("abc");
    }
}