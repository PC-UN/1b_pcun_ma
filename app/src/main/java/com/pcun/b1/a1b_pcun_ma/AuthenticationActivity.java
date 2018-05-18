package com.pcun.b1.a1b_pcun_ma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(UserConnection.checkSession(this) > 0)
            setContentView(R.layout.activity_authenticated);
        else
            setContentView(R.layout.activity_authentication);
    }

    public void onAccessPressed(View view) {
        AuthConnection authConnection = new AuthConnection();

        String username = ((EditText) findViewById(R.id.auth_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.auth_password)).getText().toString();

        authConnection.authenticate(username, password, this);
    }

    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

    public void onEndSessionPressed(View view) {
        UserConnection.closeSession(this);
        Intent intent = new Intent(this, FragmentMapActivity.class);
        intent.putExtra("from", 5);
        startActivity(intent);
    }
}